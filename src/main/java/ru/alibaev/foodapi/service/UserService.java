package ru.alibaev.foodapi.service;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.alibaev.foodapi.config.properties.KeycloakProperties;
import ru.alibaev.foodapi.mapper.UserMapper;
import ru.alibaev.foodapi.model.domain.User;
import ru.alibaev.foodapi.model.dto.request.UserRegistrationRequest;
import ru.alibaev.foodapi.model.dto.request.UserUpdateRequest;
import ru.alibaev.foodapi.model.entity.AppUserEntity;
import ru.alibaev.foodapi.model.entity.ImageEntity;
import ru.alibaev.foodapi.model.entity.base.BaseEntity;
import ru.alibaev.foodapi.repository.ImageRepository;
import ru.alibaev.foodapi.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final Keycloak keycloak;
    private final KeycloakProperties keycloakProperties;

    @Transactional
    public UUID registerUser(UserRegistrationRequest request) {

        UUID userUuid = UUID.randomUUID();
        UserRepresentation kcUser = getUserRepresentation(request, userUuid);
        UsersResource users = keycloak
                .realm(keycloakProperties.getRealm())
                .users();

        String location;
        try (Response response = users.create(kcUser)) {
            if (response.getStatus() != 201) {
                throw new RuntimeException("Keycloak create user failed: " + response.getStatus());
            }

            location = response.getHeaderString("Location");
        }
        String keycloakId = location.substring(location.lastIndexOf('/') + 1);

        // 2. Set password
        CredentialRepresentation password = new CredentialRepresentation();
        password.setType(CredentialRepresentation.PASSWORD);
        password.setValue(request.getPassword());
        password.setTemporary(false);

        users.get(keycloakId).resetPassword(password);

        // 2. Сохраняем пользователя в локальной БД
        AppUserEntity userEntity = new AppUserEntity();
        userEntity.setUuid(userUuid);
        userEntity.setLogin(request.getLogin());
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setPasswordHash(passwordEncoder.encode(request.getPassword())); // можно хранить для других целей
        userEntity.setKeycloakId(keycloakId);

        userRepository.save(userEntity);
        return userEntity.getUuid();
    }


    public User getByUuid(UUID uuid) {
        return userRepository.findByUuidAndDeletedAtIsNull(uuid)
                .map(userMapper::toDomain)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public List<User> getAll() {
        return userRepository.findAll().stream()
                .filter(u -> u.getDeletedAt() == null)
                .map(userMapper::toDomain)
                .toList();
    }

    @Transactional
    public void update(UUID uuid, UserUpdateRequest request) {
        AppUserEntity entity = userRepository.findByUuidAndDeletedAtIsNull(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (StringUtils.hasText(request.getFirstName())) {
            entity.setFirstName(request.getFirstName());
        }
        if (StringUtils.hasText(request.getLastName())) {
            entity.setLastName(request.getLastName());
        }

        if (request.getPassword() != null) {
            entity.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getAvatarImageUuid() != null) {
            ImageEntity avatar = imageRepository.findByUuid(request.getAvatarImageUuid())
                    .orElseThrow(() -> new IllegalArgumentException("Avatar not found"));
            entity.setAvatar(avatar);
        }
    }

    @Transactional
    public void softDelete(UUID uuid) {
        AppUserEntity entity = userRepository.findByUuidAndDeletedAtIsNull(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        UsersResource usersResource = keycloak.realm(keycloakProperties.getRealm()).users();
        UserRepresentation kcUser = usersResource.get(entity.getKeycloakId()).toRepresentation();
        kcUser.setEnabled(false); // блокируем вход
        usersResource.get(entity.getKeycloakId()).update(kcUser);
        userRepository.findByUuidAndDeletedAtIsNull(uuid)
                .ifPresent(BaseEntity::markDeleted);
    }

    private UserRepresentation getUserRepresentation(UserRegistrationRequest request, UUID userUuid) {
        UserRepresentation kcUser = new UserRepresentation();
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("USER_UUID", List.of(userUuid.toString()));
        kcUser.setUsername(request.getLogin());
        kcUser.setFirstName(request.getFirstName());
        kcUser.setLastName(request.getLastName());
        kcUser.setEnabled(true);
        kcUser.setAttributes(attributes);
        return kcUser;
    }
}



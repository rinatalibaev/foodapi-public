package ru.alibaev.foodapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alibaev.foodapi.config.provider.UserUuidProvider;
import ru.alibaev.foodapi.exception.NotFoundException;
import ru.alibaev.foodapi.mapper.FavoriteMapper;
import ru.alibaev.foodapi.model.domain.junction.Favorite;
import ru.alibaev.foodapi.model.entity.AppUserEntity;
import ru.alibaev.foodapi.model.entity.RecipeEntity;
import ru.alibaev.foodapi.model.entity.junction.PreparedEntity;
import ru.alibaev.foodapi.repository.FavoriteRepository;
import ru.alibaev.foodapi.repository.PreparedRepository;
import ru.alibaev.foodapi.repository.RecipeRepository;
import ru.alibaev.foodapi.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PreparedService {

    private final PreparedRepository repository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final UserUuidProvider userUuidProvider;

    @Transactional
    public void incrementPreparedCount(UUID recipeUuid) {
        UUID userUuid = userUuidProvider.provide();
        var preparedEntity = repository.findByUser_uuidAndRecipe_uuid(userUuid, recipeUuid);
        if (preparedEntity == null) {
            AppUserEntity userEntity = userRepository.findByUuid(userUuid)
                    .orElseThrow(() -> new NotFoundException("User with uuid %s not found".formatted(userUuid)));
            RecipeEntity recipeEntity = recipeRepository.findByUuid(recipeUuid)
                    .orElseThrow(() -> new NotFoundException("Recipe with uuid %s not found".formatted(recipeUuid)));
            preparedEntity = new PreparedEntity(userEntity, recipeEntity, 0);
        }
        preparedEntity.setPreparedCount(preparedEntity.getPreparedCount() + 1);
        repository.save(preparedEntity);
    }

}


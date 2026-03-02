package ru.alibaev.foodapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alibaev.foodapi.controller.api.UserApi;
import ru.alibaev.foodapi.mapper.UserMapper;
import ru.alibaev.foodapi.model.dto.request.UserRegistrationRequest;
import ru.alibaev.foodapi.model.dto.request.UserUpdateRequest;
import ru.alibaev.foodapi.model.dto.response.UserRegistrationResponse;
import ru.alibaev.foodapi.model.dto.response.UserResponse;
import ru.alibaev.foodapi.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController implements UserApi {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRegistrationResponse registerUser(@RequestBody UserRegistrationRequest request) {
        UUID uuid = userService.registerUser(request);
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setUuid(uuid);
        return response;
    }

    @Override
    @GetMapping("/{uuid}")
    public UserResponse getByUuid(@PathVariable UUID uuid) {
        return userMapper.toResponse(userService.getByUuid(uuid));
    }

    @Override
    @GetMapping
    public List<UserResponse> getAll() {
        return userService.getAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    @PutMapping("/{uuid}")
    public void update(@PathVariable UUID uuid,
                       @RequestBody UserUpdateRequest request) {
        userService.update(uuid, request);
    }

    @Override
    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable UUID uuid) {
        userService.softDelete(uuid);
    }
}


package ru.alibaev.foodapi.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alibaev.foodapi.model.dto.request.UserRegistrationRequest;
import ru.alibaev.foodapi.model.dto.request.UserUpdateRequest;
import ru.alibaev.foodapi.model.dto.response.UserRegistrationResponse;
import ru.alibaev.foodapi.model.dto.response.UserResponse;

import java.util.List;
import java.util.UUID;

@Tag(name = "Users", description = "Работа с пользователями")
public interface UserApi {

    @Operation(summary = "Регистрация нового пользователя")
    UserRegistrationResponse registerUser(@RequestBody UserRegistrationRequest request);

    @Operation(summary = "Получить пользователя по UUID")
    UserResponse getByUuid(@Parameter(description = "UUID пользователя") @PathVariable UUID uuid);

    @Operation(summary = "Обновить пользователя")
    void update(@Parameter(description = "UUID пользователя") @PathVariable UUID uuid,
                @RequestBody UserUpdateRequest request);

    @Operation(summary = "Получить всех пользователей")
    List<UserResponse> getAll();

    @Operation(summary = "Удалить пользователя (soft delete)")
    void delete(UUID uuid);
}

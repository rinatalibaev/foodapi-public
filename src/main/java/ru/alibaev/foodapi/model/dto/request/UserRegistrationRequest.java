package ru.alibaev.foodapi.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class UserRegistrationRequest {

    @NotBlank(message = "Login is required")
    @Schema(description = "Логин пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String login;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Schema(description = "Пароль пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @NotBlank(message = "First name is required")
    @Schema(description = "Имя пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;

    @Schema(description = "Фамилия пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String lastName;
}


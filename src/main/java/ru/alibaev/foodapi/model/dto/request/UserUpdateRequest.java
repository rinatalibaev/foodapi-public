package ru.alibaev.foodapi.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class UserUpdateRequest {
    @Schema(description = "Пароль пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String password;
    @Schema(description = "Имя пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String firstName;
    @Schema(description = "Фамилия пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String lastName;
    @Schema(description = "UUID аватара пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private UUID avatarImageUuid;
}

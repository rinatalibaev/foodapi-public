package ru.alibaev.foodapi.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {

    @Schema(description = "UUID пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID uuid;
    @Schema(description = "Имя пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;
    @Schema(description = "Фамилия пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String lastName;
    @Schema(description = "Фото пользователя", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private ImageResponse avatar;
}

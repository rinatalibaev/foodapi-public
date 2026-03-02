package ru.alibaev.foodapi.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class UserRegistrationResponse {
    @Schema(description = "UUID зарегистрированного пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID uuid;
}

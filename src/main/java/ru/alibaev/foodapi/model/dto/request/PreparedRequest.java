package ru.alibaev.foodapi.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class PreparedRequest {
    @Schema(description = "UUID рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID recipeUuid;
}

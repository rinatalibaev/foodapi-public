package ru.alibaev.foodapi.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class RecipeCreateResponse {
    @Schema(description = "UUID созданного рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID uuid;
}

package ru.alibaev.foodapi.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class FavoriteResponse {
    @Schema(description = "UUID рецепта")
    private UUID recipeUuid;
}

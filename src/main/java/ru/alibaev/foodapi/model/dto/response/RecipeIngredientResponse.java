package ru.alibaev.foodapi.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class RecipeIngredientResponse {
    @Schema(description = "UUID ингредиента рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID uuid;
    @Schema(description = "Название ингредиента", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ingredientName;
    @Schema(description = "Количество ингредиента в рецепте", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal count;
    @Schema(description = "Единица измерения ингредиента рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private String measureUnit;
}

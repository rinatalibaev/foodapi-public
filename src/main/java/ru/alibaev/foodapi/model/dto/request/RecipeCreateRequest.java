package ru.alibaev.foodapi.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class RecipeCreateRequest {
    @Schema(description = "Название рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(description = "Шаги приготовления", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<StepRequest> steps;
    @Schema(description = "Ингредиенты рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<RecipeIngredientRequest> ingredients;
    @Schema(description = "UUID изображения готового блюда", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private UUID photoUuid;
}

package ru.alibaev.foodapi.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class RecipeResponse {
    @Schema(description = "UUID рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID uuid;
    @Schema(description = "Название рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(description = "Время приготовления рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer duration;
    @Schema(description = "Автор рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private UserResponse author;
    @Schema(description = "Изображение блюда рецепта", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private ImageResponse image;
    @Schema(description = "Шаги приготовления", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<StepResponse> steps;
    @Schema(description = "Ингредиенты рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<RecipeIngredientResponse> ingredients;
    @Schema(description = "Комментарии к рецепту", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Set<CommentResponse> comments;
    @Schema(description = "Флаг избранного рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean favorite;
    @Schema(description = "Количество приготовления рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private int preparedCount;
}


package ru.alibaev.foodapi.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class RecipeListResponse {
    @Schema(description = "UUID рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID uuid;
    @Schema(description = "Название рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(description = "Время приготовления рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer duration;
    @Schema(description = "Сведения об изображении рецепта", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private ImageResponse image;
    @Schema(description = "Флаг избранного рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private boolean favorite;
    @Schema(description = "Количество приготовления рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private int preparedCount;
}


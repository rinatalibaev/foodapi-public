package ru.alibaev.foodapi.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class IngredientResponse {
    @Schema(description = "UUID ингредиента", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID uuid;
    @Schema(description = "Название ингредиента", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
}

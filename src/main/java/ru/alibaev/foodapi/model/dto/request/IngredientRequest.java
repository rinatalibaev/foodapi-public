package ru.alibaev.foodapi.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class IngredientRequest {
    @Schema(description = "Название ингредиента", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(description = "UUID единицы измерения", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID measureUnitUuid;
}

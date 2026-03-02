package ru.alibaev.foodapi.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class FreezerChangeRequest {
    @Schema(description = "UUID ингредиента", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID ingredientUuid;

    @Schema(description = "UUID единицы измерения", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID measureUnitUuid;

    @Schema(description = "Количество", example = "2.5", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal count;
}


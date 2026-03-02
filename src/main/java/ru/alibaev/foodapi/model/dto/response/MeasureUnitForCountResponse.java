package ru.alibaev.foodapi.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class MeasureUnitForCountResponse {
    @Schema(description = "UUID единицы измерения для определенного количества ингредиента", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID uuid;
    @Schema(description = "Количество ингредиента", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal count;
    @Schema(description = "Название единицы измерения для определенного количества ингредиента", requiredMode = Schema.RequiredMode.REQUIRED)
    private String measureUnit;
}

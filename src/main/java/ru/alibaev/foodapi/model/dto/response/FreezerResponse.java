package ru.alibaev.foodapi.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FreezerResponse {
    @Schema(description = "Название ингредиента", requiredMode = Schema.RequiredMode.REQUIRED)
    private String ingredientName;
    @Schema(description = "Название единицы измерения", requiredMode = Schema.RequiredMode.REQUIRED)
    private String measureUnit;
    @Schema(description = "Количество ингредиента", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal count;
}

package ru.alibaev.foodapi.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class MeasureUnitResponse {
    @Schema(description = "UUID единицы измерения", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID uuid;
    @Schema(description = "Название единицы измерения в единственном числе", requiredMode = Schema.RequiredMode.REQUIRED)
    private String one;
    @Schema(description = "Название единицы измерения во множественном числе (несколько)", requiredMode = Schema.RequiredMode.REQUIRED)
    private String few;
    @Schema(description = "Название единицы измерения во множественном числе (много)", requiredMode = Schema.RequiredMode.REQUIRED)
    private String many;
}

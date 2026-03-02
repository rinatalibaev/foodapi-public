package ru.alibaev.foodapi.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class StepResponse {
    @Schema(description = "UUID шага рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID uuid;
    @Schema(description = "Номер шага приготовления", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer stepOrder;
    @Schema(description = "Имя шага приготовления", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(description = "Продолжительность шага приготовления", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer duration;
}

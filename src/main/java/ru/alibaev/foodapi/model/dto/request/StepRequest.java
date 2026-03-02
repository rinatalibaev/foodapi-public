package ru.alibaev.foodapi.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class StepRequest {
    @Schema(description = "Номер шага приготовления", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer stepOrder;
    @Schema(description = "Имя шага приготовления", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(description = "Продолжительность шага приготовления", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer duration;
}

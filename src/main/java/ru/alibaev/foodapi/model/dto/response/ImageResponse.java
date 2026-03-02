package ru.alibaev.foodapi.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class ImageResponse {

    @Schema(description = "UUID изображения", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID uuid;
}

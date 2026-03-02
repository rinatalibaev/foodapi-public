package ru.alibaev.foodapi.model.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class CommentResponse {

    @Schema(description = "UUID комментария", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID uuid;

    @Schema(description = "Текст комментария", requiredMode = Schema.RequiredMode.REQUIRED)
    private String text;

    @Schema(description = "Автор комментария", requiredMode = Schema.RequiredMode.REQUIRED)
    private UserResponse author;

    @Schema(description = "Изображение комментария", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private ImageResponse image;

    @Schema(description = "Дата создания комментария", requiredMode = Schema.RequiredMode.REQUIRED)
    private OffsetDateTime createdAt;
}


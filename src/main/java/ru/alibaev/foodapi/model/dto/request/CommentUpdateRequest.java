package ru.alibaev.foodapi.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CommentUpdateRequest {
    @Schema(description = "Текст комментария", requiredMode = Schema.RequiredMode.REQUIRED)
    private String text;
}

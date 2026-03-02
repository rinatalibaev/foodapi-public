package ru.alibaev.foodapi.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class CommentCreateRequest {

    @Schema(description = "UUID рецепта", requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID recipeUuid;

    @Schema(description = "UUID картинки комментария", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private UUID imageUuid;

    @Schema(description = "Текст комментария", requiredMode = Schema.RequiredMode.REQUIRED)
    private String text;

}


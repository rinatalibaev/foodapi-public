package ru.alibaev.foodapi.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alibaev.foodapi.model.dto.request.CommentCreateRequest;
import ru.alibaev.foodapi.model.dto.request.CommentUpdateRequest;
import ru.alibaev.foodapi.model.dto.response.CommentResponse;

import java.util.UUID;

@Tag(name = "Comments", description = "АПИ комментариев к рецептам")
public interface CommentApi {

    @Operation(summary = "Добавить комментарий к рецепту")
    CommentResponse addComment(
            @RequestBody CommentCreateRequest request
    );

    @Operation(summary = "Редактировать комментарий")
    CommentResponse updateComment(
            @PathVariable UUID uuid,
            @RequestBody CommentUpdateRequest request
    );

    @Operation(summary = "Удалить комментарий (soft delete)")
    void deleteComment(@PathVariable UUID uuid);
}

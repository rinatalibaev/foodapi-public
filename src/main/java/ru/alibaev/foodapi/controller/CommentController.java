package ru.alibaev.foodapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alibaev.foodapi.controller.api.CommentApi;
import ru.alibaev.foodapi.mapper.CommentMapper;
import ru.alibaev.foodapi.model.dto.request.CommentCreateRequest;
import ru.alibaev.foodapi.model.dto.request.CommentUpdateRequest;
import ru.alibaev.foodapi.model.dto.response.CommentResponse;
import ru.alibaev.foodapi.service.CommentService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController implements CommentApi {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @Override
    @PostMapping
    public CommentResponse addComment(@RequestBody CommentCreateRequest request) {
        return commentMapper.toResponse(commentService.addComment(request));
    }

    @Override
    @PutMapping("/{uuid}")
    public CommentResponse updateComment(@PathVariable UUID uuid, @RequestBody CommentUpdateRequest request) {
        return commentMapper.toResponse(commentService.updateComment(uuid, request.getText()));
    }

    @Override
    @DeleteMapping("/{uuid}")
    public void deleteComment(@PathVariable UUID uuid) {
        commentService.deleteComment(uuid);
    }
}


package ru.alibaev.foodapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alibaev.foodapi.config.provider.UserUuidProvider;
import ru.alibaev.foodapi.mapper.CommentMapper;
import ru.alibaev.foodapi.model.domain.Comment;
import ru.alibaev.foodapi.model.dto.request.CommentCreateRequest;
import ru.alibaev.foodapi.model.entity.AppUserEntity;
import ru.alibaev.foodapi.model.entity.CommentEntity;
import ru.alibaev.foodapi.model.entity.ImageEntity;
import ru.alibaev.foodapi.model.entity.RecipeEntity;
import ru.alibaev.foodapi.repository.CommentRepository;
import ru.alibaev.foodapi.repository.ImageRepository;
import ru.alibaev.foodapi.repository.RecipeRepository;
import ru.alibaev.foodapi.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final RecipeRepository recipeRepository;
    private final CommentMapper commentMapper;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final UserUuidProvider userUuidProvider;

    public Comment addComment(CommentCreateRequest request) {
        CommentEntity entity = new CommentEntity();
        RecipeEntity recipeEntity = recipeRepository.findByUuidAndDeletedAtIsNull(request.getRecipeUuid())
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));
        AppUserEntity appUserEntity = userRepository.findByUuidAndDeletedAtIsNull(userUuidProvider.provide())
                .orElseThrow(() -> new IllegalStateException("User not found"));
        entity.setRecipe(recipeEntity);
        entity.setAuthor(appUserEntity);
        entity.setText(request.getText());
        if (request.getImageUuid() != null) {
            ImageEntity image = imageRepository.findByUuid(request.getImageUuid())
                    .orElseThrow(() -> new IllegalArgumentException("Image not found"));
            entity.setImage(image);
        }
        return commentMapper.toDomain(commentRepository.save(entity));
    }

    public Comment updateComment(UUID commentUuid, String text) {
        CommentEntity entity = commentRepository.findByUuid(commentUuid)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        if (!entity.getAuthor().getUuid().equals(userUuidProvider.provide())) {
            throw new AccessDeniedException("You are not the author of this comment");
        }
        entity.setText(text);
        return commentMapper.toDomain(entity);
    }

    public void deleteComment(UUID commentUuid) {
        CommentEntity entity = commentRepository.findByUuid(commentUuid)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        if (!entity.getAuthor().getUuid().equals(userUuidProvider.provide())) {
            throw new AccessDeniedException("You are not the author of this comment");
        }
        entity.markDeleted();
    }

}


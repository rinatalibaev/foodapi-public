package ru.alibaev.foodapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alibaev.foodapi.model.entity.CommentEntity;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    Optional<CommentEntity> findByUuid(UUID uuid);
    Set<CommentEntity> findAllByRecipe_UuidAndDeletedAtIsNull(UUID recipeUuid);
}


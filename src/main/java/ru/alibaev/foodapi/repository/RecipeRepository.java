package ru.alibaev.foodapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.alibaev.foodapi.model.entity.RecipeEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    Optional<RecipeEntity> findByUuid(UUID uuid);
    Page<RecipeEntity> findAllByDeletedAtIsNull(Pageable pageable);
    Optional<RecipeEntity> findByUuidAndDeletedAtIsNull(UUID uuid);
    List<RecipeEntity> findAllByUuidInAndDeletedAtIsNull(List<UUID> uuids);
}


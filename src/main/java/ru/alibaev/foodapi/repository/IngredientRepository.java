package ru.alibaev.foodapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alibaev.foodapi.model.entity.IngredientEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {
    Optional<IngredientEntity> findByUuid(UUID uuid);
    List<IngredientEntity> findAllByDeletedAtIsNull();
}

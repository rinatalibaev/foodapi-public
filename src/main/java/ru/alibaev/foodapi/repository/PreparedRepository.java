package ru.alibaev.foodapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alibaev.foodapi.model.entity.junction.PreparedEntity;

import java.util.List;
import java.util.UUID;

public interface PreparedRepository extends JpaRepository<PreparedEntity, Long> {
    List<PreparedEntity> findAllByUserUuid(UUID userUuid);
    PreparedEntity findByUser_uuidAndRecipe_uuid(UUID userUuid, UUID recipeUuid);

}


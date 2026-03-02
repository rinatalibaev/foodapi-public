package ru.alibaev.foodapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alibaev.foodapi.model.entity.junction.FreezerEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FreezerRepository extends JpaRepository<FreezerEntity, Long> {
    Optional<FreezerEntity> findByUser_UuidAndIngredient_UuidAndMeasureUnit_Uuid(
            UUID userUuid,
            UUID ingredientUuid,
            UUID measureUnitUuid
    );

    List<FreezerEntity> findAllByUser_Uuid(UUID userUuid);

    void deleteByUser_UuidAndIngredient_UuidAndMeasureUnit_Uuid(
            UUID userUuid,
            UUID ingredientUuid,
            UUID measureUnitUuid
    );
}


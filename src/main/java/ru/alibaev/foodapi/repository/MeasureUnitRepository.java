package ru.alibaev.foodapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.alibaev.foodapi.model.entity.MeasureUnitEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MeasureUnitRepository extends JpaRepository<MeasureUnitEntity, Long> {
    Optional<MeasureUnitEntity> findByUuid(UUID uuid);
    List<MeasureUnitEntity> findAllByDeletedAtIsNull();

    @Query("""
        select imu.measureUnit
        from IngredientMeasureUnitEntity imu
        where imu.ingredient.uuid = :ingredientUuid
    """)
    List<MeasureUnitEntity> findAllByIngredientUuid(UUID ingredientUuid);
}


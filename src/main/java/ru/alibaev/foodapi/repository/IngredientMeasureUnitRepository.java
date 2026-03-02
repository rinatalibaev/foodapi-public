package ru.alibaev.foodapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alibaev.foodapi.model.entity.junction.IngredientMeasureUnitEntity;

public interface IngredientMeasureUnitRepository extends JpaRepository<IngredientMeasureUnitEntity, Long> {

}

package ru.alibaev.foodapi.model.entity.junction;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alibaev.foodapi.model.entity.IngredientEntity;
import ru.alibaev.foodapi.model.entity.MeasureUnitEntity;
import ru.alibaev.foodapi.model.entity.base.BaseIdEntity;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ingredient_measure_unit")
@Getter
@Setter
public class IngredientMeasureUnitEntity extends BaseIdEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private IngredientEntity ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measure_unit_id", nullable = false)
    private MeasureUnitEntity measureUnit;
}


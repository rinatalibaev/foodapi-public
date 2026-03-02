package ru.alibaev.foodapi.model.entity.junction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.alibaev.foodapi.model.entity.IngredientEntity;
import ru.alibaev.foodapi.model.entity.MeasureUnitEntity;
import ru.alibaev.foodapi.model.entity.RecipeEntity;
import ru.alibaev.foodapi.model.entity.base.BaseIdEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "recipe_ingredient")
@Getter
@Setter
public class RecipeIngredientEntity extends BaseIdEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipe_id", nullable = false, foreignKey = @ForeignKey(name = "fk_ri_recipe"))
    private RecipeEntity recipe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingredient_id", nullable = false, foreignKey = @ForeignKey(name = "fk_ri_ingredient"))
    private IngredientEntity ingredient;

    @Column(name = "count", precision = 10, scale = 2)
    private BigDecimal count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measure_unit_id", foreignKey = @ForeignKey(name = "fk_ri_measure_unit"))
    private MeasureUnitEntity measureUnit;

}

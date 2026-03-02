package ru.alibaev.foodapi.model.entity.junction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import ru.alibaev.foodapi.model.entity.AppUserEntity;
import ru.alibaev.foodapi.model.entity.IngredientEntity;
import ru.alibaev.foodapi.model.entity.MeasureUnitEntity;
import ru.alibaev.foodapi.model.entity.base.BaseIdEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "freezer")
@Getter
@Setter
public class FreezerEntity extends BaseIdEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private IngredientEntity ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measure_unit_id", nullable = false)
    private MeasureUnitEntity measureUnit;

    @Column(nullable = false)
    private BigDecimal count;
}


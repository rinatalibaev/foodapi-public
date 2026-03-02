package ru.alibaev.foodapi.model.domain.junction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.alibaev.foodapi.model.domain.Ingredient;
import ru.alibaev.foodapi.model.domain.MeasureUnit;
import ru.alibaev.foodapi.model.domain.base.BaseIdDomain;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class RecipeIngredient extends BaseIdDomain {
    private Ingredient ingredient;
    private BigDecimal count;
    private MeasureUnit measureUnit;
}


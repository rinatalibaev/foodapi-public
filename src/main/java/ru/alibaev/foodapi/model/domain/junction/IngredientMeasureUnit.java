package ru.alibaev.foodapi.model.domain.junction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.alibaev.foodapi.model.domain.base.BaseIdDomain;

@Data
@EqualsAndHashCode(callSuper = true)
public class IngredientMeasureUnit extends BaseIdDomain {
    private Long ingredientId;
    private Long measureUnitId;
}


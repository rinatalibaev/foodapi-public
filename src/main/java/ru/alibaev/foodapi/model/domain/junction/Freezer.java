package ru.alibaev.foodapi.model.domain.junction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.alibaev.foodapi.model.domain.base.BaseIdDomain;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Freezer extends BaseIdDomain {
    private UUID userUuid;
    private String ingredientName;
    private String measureUnit;
    private BigDecimal count;
}


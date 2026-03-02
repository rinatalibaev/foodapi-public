package ru.alibaev.foodapi.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.alibaev.foodapi.model.domain.base.BaseDomain;

@Data
@EqualsAndHashCode(callSuper = true)
public class Ingredient extends BaseDomain {
    private String name;
}


package ru.alibaev.foodapi.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.alibaev.foodapi.model.domain.base.BaseDomain;
import ru.alibaev.foodapi.model.entity.ManyFieldable;

@Data
@EqualsAndHashCode(callSuper = true)
public class MeasureUnit extends BaseDomain implements ManyFieldable {
    private String one;
    private String few;
    private String many;
}


package ru.alibaev.foodapi.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode
public class MeasureUnitForCount {
    private UUID uuid;
    private BigDecimal count;
    private String measureUnit;
}


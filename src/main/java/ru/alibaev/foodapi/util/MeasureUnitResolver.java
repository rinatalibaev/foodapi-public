package ru.alibaev.foodapi.util;

import lombok.experimental.UtilityClass;
import ru.alibaev.foodapi.model.entity.ManyFieldable;

import java.math.BigDecimal;

@UtilityClass
public class MeasureUnitResolver {

    public String resolveMeasureUnitName(ManyFieldable manyFieldable, BigDecimal count) {
        int value = count.abs().intValue();

        int lastTwoDigits = value % 100;
        int lastDigit = value % 10;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 14) {
            return manyFieldable.getMany();
        }

        if (lastDigit == 1) {
            return manyFieldable.getOne();
        }

        if (lastDigit >= 2 && lastDigit <= 4) {
            return manyFieldable.getFew();
        }

        return manyFieldable.getMany();
    }

}

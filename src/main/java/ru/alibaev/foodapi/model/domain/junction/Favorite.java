package ru.alibaev.foodapi.model.domain.junction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.alibaev.foodapi.model.domain.base.BaseIdDomain;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class Favorite extends BaseIdDomain {
    private UUID recipeUuid;
}


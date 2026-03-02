package ru.alibaev.foodapi.mapper;

import org.mapstruct.Mapper;
import ru.alibaev.foodapi.model.domain.Ingredient;
import ru.alibaev.foodapi.model.dto.response.IngredientResponse;
import ru.alibaev.foodapi.model.entity.IngredientEntity;

@Mapper(uses = {MeasureUnitMapper.class})
public interface IngredientMapper {

    Ingredient toDomain(IngredientEntity entity);

    IngredientEntity toEntity(Ingredient domain);

    IngredientResponse toResponse(Ingredient domain);
}


package ru.alibaev.foodapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.alibaev.foodapi.model.domain.junction.Freezer;
import ru.alibaev.foodapi.model.dto.response.FreezerResponse;
import ru.alibaev.foodapi.model.entity.junction.FreezerEntity;
import ru.alibaev.foodapi.util.MeasureUnitResolver;

@Mapper(imports = {MeasureUnitResolver.class})
public interface FreezerMapper {

    @Mapping(target = "userUuid", source = "entity.user.uuid")
    @Mapping(target = "ingredientName", source = "ingredient.name")
    @Mapping(target = "measureUnit", expression = "java(MeasureUnitResolver.resolveMeasureUnitName(entity.getMeasureUnit(), entity.getCount()))")
    Freezer toDomain(FreezerEntity entity);

    FreezerResponse toResponse(Freezer domain);
}


package ru.alibaev.foodapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.alibaev.foodapi.model.domain.junction.RecipeIngredient;
import ru.alibaev.foodapi.model.dto.response.RecipeIngredientResponse;
import ru.alibaev.foodapi.util.MeasureUnitResolver;

@Mapper(uses = {IngredientMapper.class, MeasureUnitMapper.class}, imports = {MeasureUnitResolver.class})
public interface RecipeIngredientMapper {
//    RecipeIngredient toDomain(RecipeIngredientEntity entity);
//    RecipeIngredientEntity toEntity(RecipeIngredient domain);

//    RecipeIngredient toDomain(RecipeIngredientEntity entity);
//
//    RecipeIngredientEntity toEntity(RecipeIngredient domain);

    @Mapping(target = "measureUnit", expression = "java(MeasureUnitResolver.resolveMeasureUnitName(domain.getMeasureUnit(), domain.getCount()))")
    @Mapping(target = "ingredientName", source = "ingredient.name")
    @Mapping(target = "uuid", source = "ingredient.uuid")
    RecipeIngredientResponse toResponse(RecipeIngredient domain);

//    @Mapping(target = "ingredient", ignore = true)
//    @Mapping(target = "measureUnit", ignore = true)
//    RecipeIngredient toDomain(RecipeIngredientRequest request);
}


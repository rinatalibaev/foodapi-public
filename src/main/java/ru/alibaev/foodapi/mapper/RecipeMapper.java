package ru.alibaev.foodapi.mapper;

import org.mapstruct.Mapper;
import ru.alibaev.foodapi.model.domain.Recipe;
import ru.alibaev.foodapi.model.dto.response.RecipeListResponse;
import ru.alibaev.foodapi.model.dto.response.RecipeResponse;
import ru.alibaev.foodapi.model.entity.RecipeEntity;

import java.util.List;

@Mapper(uses = {UserMapper.class, ImageMapper.class, StepMapper.class, RecipeIngredientMapper.class, CommentMapper.class})
public interface RecipeMapper {

    Recipe toDomain(RecipeEntity entity);

    RecipeEntity toEntity(Recipe domain);

    List<Recipe> toDomainList(List<RecipeEntity> entities);

    RecipeListResponse toListResponse(Recipe recipe);
    RecipeResponse toResponse(Recipe recipe);
}


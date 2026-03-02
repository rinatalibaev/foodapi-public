package ru.alibaev.foodapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.alibaev.foodapi.model.domain.junction.Favorite;
import ru.alibaev.foodapi.model.dto.response.FavoriteResponse;
import ru.alibaev.foodapi.model.entity.junction.FavoriteEntity;

@Mapper
public interface FavoriteMapper {
    @Mapping(target = "recipeUuid", source = "entity.recipe.uuid")
    Favorite toDomain(FavoriteEntity entity);

    FavoriteResponse toResponse(Favorite favorite);
}


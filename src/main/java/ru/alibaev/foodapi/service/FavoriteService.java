package ru.alibaev.foodapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alibaev.foodapi.config.provider.UserUuidProvider;
import ru.alibaev.foodapi.mapper.FavoriteMapper;
import ru.alibaev.foodapi.model.domain.Recipe;
import ru.alibaev.foodapi.model.domain.junction.Favorite;
import ru.alibaev.foodapi.model.entity.base.BaseEntity;
import ru.alibaev.foodapi.model.entity.junction.FavoriteEntity;
import ru.alibaev.foodapi.repository.FavoriteRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteService {

    private final FavoriteRepository repository;
    private final FavoriteMapper mapper;
    private final UserUuidProvider userUuidProvider;
//    private final RecipeService recipeService;

    public void addFavorite(Favorite favorite) {
        repository.addFavorite(userUuidProvider.provide(), favorite.getRecipeUuid());
    }

    public void deleteFavorite(UUID recipeUuid) {
        repository.deleteByUser_UuidAndRecipe_Uuid(userUuidProvider.provide(), recipeUuid);
    }

//    public List<Recipe> getUserFavorites() {
//        return recipeService.getRecipesByUuids(
//                repository.findAllByUser_Uuid(userUuidProvider.provide()).stream()
//                        .map(FavoriteEntity::getRecipe)
//                        .map(BaseEntity::getUuid)
//                        .toList());
//    }
}


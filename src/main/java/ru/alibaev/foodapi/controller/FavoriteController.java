package ru.alibaev.foodapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alibaev.foodapi.controller.api.FavoriteApi;
import ru.alibaev.foodapi.mapper.RecipeMapper;
import ru.alibaev.foodapi.model.domain.Recipe;
import ru.alibaev.foodapi.model.domain.junction.Favorite;
import ru.alibaev.foodapi.model.dto.request.FavoriteRequest;
import ru.alibaev.foodapi.model.dto.response.RecipeResponse;
import ru.alibaev.foodapi.service.FavoriteService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/favorites")
public class FavoriteController implements FavoriteApi {

    private final FavoriteService favoriteService;
    private final RecipeMapper recipeMapper;

    @Override
    @PostMapping
    public void addFavorite(@RequestBody FavoriteRequest request) {
        Favorite favorite = new Favorite();
        favorite.setRecipeUuid(request.getRecipeUuid());
        favoriteService.addFavorite(favorite);
    }

    @Override
    @DeleteMapping("/{recipeUuid}")
    public void removeFavorite(@PathVariable UUID recipeUuid) {
        favoriteService.deleteFavorite(recipeUuid);
    }

//    @Override
//    @GetMapping
//    public List<RecipeResponse> getFavorites() {
//        List<Recipe> favoriteRecipes = favoriteService.getUserFavorites();
//        return favoriteRecipes.stream()
//                .map(recipeMapper::toResponse)
//                .toList();
//    }

}


package ru.alibaev.foodapi.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import ru.alibaev.foodapi.model.dto.request.FavoriteRequest;
import ru.alibaev.foodapi.model.dto.response.RecipeResponse;

import java.util.List;
import java.util.UUID;

@Tag(name = "Favorites", description = "АПИ избранных рецептов")
public interface FavoriteApi {

    @Operation(summary = "Добавить рецепт в избранное")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Рецепт добавлен в избранное"),
            @ApiResponse(responseCode = "404", description = "Рецепт не найден")
    })
    void addFavorite(@RequestBody FavoriteRequest request);

    @Operation(summary = "Удалить рецепт из избранного")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Рецепт удалён из избранного"),
            @ApiResponse(responseCode = "404", description = "Рецепт не найден")
    })
    void removeFavorite(@PathVariable UUID recipeUuid);

//    @Operation(summary = "Список избранных рецептов пользователя")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Список избранных рецептов")
//    })
//    List<RecipeResponse> getFavorites();
}


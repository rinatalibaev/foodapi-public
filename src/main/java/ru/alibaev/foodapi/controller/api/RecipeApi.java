package ru.alibaev.foodapi.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alibaev.foodapi.model.dto.request.RecipeCreateRequest;
import ru.alibaev.foodapi.model.dto.response.RecipeCreateResponse;
import ru.alibaev.foodapi.model.dto.response.RecipeListResponse;
import ru.alibaev.foodapi.model.dto.response.RecipeResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Tag(name = "Recipes", description = "АПИ рецептов")
public interface RecipeApi {

    @Operation(summary = "Создать рецепт с фото")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Рецепт успешно создан",
                    content = @Content(schema = @Schema(implementation = RecipeCreateResponse.class))),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    })
    RecipeCreateResponse createRecipe(
            @RequestBody RecipeCreateRequest request
    ) throws IOException;

    @Operation(summary = "Получить все рецепты")
    List<RecipeListResponse> getAllRecipes(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size);

    @Operation(summary = "Получить рецепт по UUID")
    RecipeResponse getRecipeByUuid(@PathVariable UUID uuid);

    @Operation(summary = "Удалить рецепт (soft delete)")
    void deleteRecipe(@PathVariable UUID uuid);
}


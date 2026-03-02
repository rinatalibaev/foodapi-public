package ru.alibaev.foodapi.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.alibaev.foodapi.model.dto.request.IngredientRequest;
import ru.alibaev.foodapi.model.dto.response.IngredientResponse;

import java.util.List;
import java.util.UUID;

@Tag(name = "Ingredients", description = "Работа с ингредиентами")
public interface IngredientApi {

    @Operation(summary = "Создать ингредиент")
    UUID createIngredient(IngredientRequest request);

    @Operation(summary = "Получить список ингредиентов")
    List<IngredientResponse> getAllIngredients();
}

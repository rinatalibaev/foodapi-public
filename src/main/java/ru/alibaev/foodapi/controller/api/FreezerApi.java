package ru.alibaev.foodapi.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.alibaev.foodapi.model.dto.request.FreezerChangeRequest;
import ru.alibaev.foodapi.model.dto.response.FreezerResponse;

import java.util.List;

@Tag(name = "Freezer", description = "Холодильник пользователя")
public interface FreezerApi {

    @Operation(summary = "Добавить ингредиент в холодильник")
    void addIngredient(
            @RequestBody FreezerChangeRequest request
    );

    @Operation(summary = "Уменьшить количество ингредиента в холодильнике")
    void subtractIngredient(
            @RequestBody FreezerChangeRequest request
    );

    @Operation(summary = "Получить холодильник текущего пользователя")
    List<FreezerResponse> getMyFreezer();
}


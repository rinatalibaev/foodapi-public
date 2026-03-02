package ru.alibaev.foodapi.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.alibaev.foodapi.model.dto.request.FavoriteRequest;
import ru.alibaev.foodapi.model.dto.request.PreparedRequest;

import java.util.UUID;

@Tag(name = "Prepared", description = "АПИ приготовленных рецептов")
public interface PreparedApi {

    @Operation(summary = "Увеличить количество приготовлений блюда на 1")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Счетчик обновлен"),
            @ApiResponse(responseCode = "404", description = "Рецепт не найден")
    })
    void incrementPreparedCount(@RequestBody PreparedRequest preparedRequest);
}


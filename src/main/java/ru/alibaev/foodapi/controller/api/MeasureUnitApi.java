package ru.alibaev.foodapi.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ru.alibaev.foodapi.model.dto.request.MeasureUnitCreateRequest;
import ru.alibaev.foodapi.model.dto.response.MeasureUnitForCountResponse;
import ru.alibaev.foodapi.model.dto.response.MeasureUnitResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Tag(name = "MeasureUnits", description = "Единицы измерения ингредиентов")
public interface MeasureUnitApi {

    @Operation(summary = "Создать единицу измерения")
    @ApiResponse(responseCode = "201", description = "Создано")
    void create(MeasureUnitCreateRequest request);

    @Operation(summary = "Получить список доступных единиц измерения для определенного количества ингредиента")
    @ApiResponse(responseCode = "200", description = "Успешно")
    List<MeasureUnitForCountResponse> getForCount(UUID ingredientUuid, BigDecimal count);

    @Operation(summary = "Получить все единицы измерения")
    @ApiResponse(responseCode = "200", description = "Успешно")
    List<MeasureUnitResponse> getAll();
}


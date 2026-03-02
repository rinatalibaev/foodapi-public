package ru.alibaev.foodapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.alibaev.foodapi.controller.api.MeasureUnitApi;
import ru.alibaev.foodapi.mapper.MeasureUnitMapper;
import ru.alibaev.foodapi.model.dto.request.MeasureUnitCreateRequest;
import ru.alibaev.foodapi.model.dto.response.MeasureUnitForCountResponse;
import ru.alibaev.foodapi.model.dto.response.MeasureUnitResponse;
import ru.alibaev.foodapi.service.MeasureUnitService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/measure-units")
public class MeasureUnitController implements MeasureUnitApi {

    private final MeasureUnitService service;
    private final MeasureUnitMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public void create(@RequestBody MeasureUnitCreateRequest request) {
        service.create(mapper.toDomain(request));
    }

    @GetMapping("/for-count")
    @Override
    public List<MeasureUnitForCountResponse> getForCount(UUID ingredientUuid, BigDecimal count) {
        return service.getMeasureUnitForIngredientCount(ingredientUuid, count).stream()
                .map(mapper::toForCountResponse)
                .toList();
    }

    @GetMapping
    @Override
    public List<MeasureUnitResponse> getAll() {
        return service.getAll().stream()
                .map(mapper::toResponse)
                .toList();
    }
}

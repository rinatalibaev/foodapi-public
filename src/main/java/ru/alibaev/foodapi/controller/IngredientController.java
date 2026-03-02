package ru.alibaev.foodapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.alibaev.foodapi.controller.api.IngredientApi;
import ru.alibaev.foodapi.mapper.IngredientMapper;
import ru.alibaev.foodapi.model.dto.request.IngredientRequest;
import ru.alibaev.foodapi.model.dto.response.IngredientResponse;
import ru.alibaev.foodapi.service.IngredientService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController implements IngredientApi {

    private final IngredientService ingredientService;
    private final IngredientMapper dtoMapper;

    @Override
    @PostMapping
    public UUID createIngredient(@RequestBody IngredientRequest request) {
        return ingredientService.createIngredient(
                request.getName(), request.getMeasureUnitUuid()
        );
    }

    @Override
    @GetMapping
    public List<IngredientResponse> getAllIngredients() {
        return ingredientService.getAllIngredients().stream()
                .map(dtoMapper::toResponse)
                .collect(Collectors.toList());
    }
}


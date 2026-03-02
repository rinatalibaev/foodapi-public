package ru.alibaev.foodapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alibaev.foodapi.mapper.IngredientMapper;
import ru.alibaev.foodapi.model.domain.Ingredient;
import ru.alibaev.foodapi.model.entity.IngredientEntity;
import ru.alibaev.foodapi.model.entity.MeasureUnitEntity;
import ru.alibaev.foodapi.model.entity.junction.IngredientMeasureUnitEntity;
import ru.alibaev.foodapi.repository.IngredientMeasureUnitRepository;
import ru.alibaev.foodapi.repository.IngredientRepository;
import ru.alibaev.foodapi.repository.MeasureUnitRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final MeasureUnitRepository measureUnitRepository;
    private final IngredientMeasureUnitRepository ingredientMeasureUnitRepository;
    private final IngredientMapper ingredientMapper;

    @Transactional
    public UUID createIngredient(String ingredientName, UUID measureUnitUuid) {

        IngredientEntity ingredientEntity = new IngredientEntity();
        ingredientEntity.setName(ingredientName);

        ingredientEntity = ingredientRepository.save(ingredientEntity);

        MeasureUnitEntity measureUnitEntity = measureUnitRepository.findByUuid(measureUnitUuid)
                .orElseThrow(() -> new IllegalArgumentException(
                        "MeasureUnit not found: " + measureUnitUuid
                ));

        IngredientMeasureUnitEntity ingredientMeasureUnitEntity = new IngredientMeasureUnitEntity();
        ingredientMeasureUnitEntity.setIngredient(ingredientEntity);
        ingredientMeasureUnitEntity.setMeasureUnit(measureUnitEntity);

        ingredientMeasureUnitRepository.save(ingredientMeasureUnitEntity);

        return ingredientEntity.getUuid();
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAllByDeletedAtIsNull().stream()
                .map(ingredientMapper::toDomain)
                .collect(Collectors.toList());
    }
}


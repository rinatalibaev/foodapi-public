package ru.alibaev.foodapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alibaev.foodapi.config.provider.UserUuidProvider;
import ru.alibaev.foodapi.mapper.FreezerMapper;
import ru.alibaev.foodapi.model.domain.junction.Freezer;
import ru.alibaev.foodapi.model.dto.request.FreezerChangeRequest;
import ru.alibaev.foodapi.model.entity.junction.FreezerEntity;
import ru.alibaev.foodapi.repository.FreezerRepository;
import ru.alibaev.foodapi.repository.IngredientRepository;
import ru.alibaev.foodapi.repository.MeasureUnitRepository;
import ru.alibaev.foodapi.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class FreezerService {

    private final FreezerRepository repository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;
    private final MeasureUnitRepository measureUnitRepository;
    private final FreezerMapper mapper;
    private final UserUuidProvider userUuidProvider;
    private final FreezerRepository freezerRepository;

    public void addIngredient(FreezerChangeRequest request) {
        UUID userUuid = userUuidProvider.provide();

        FreezerEntity entity = repository
                .findByUser_UuidAndIngredient_UuidAndMeasureUnit_Uuid(
                        userUuid,
                        request.getIngredientUuid(),
                        request.getMeasureUnitUuid()
                )
                .orElseGet(() -> {
                    FreezerEntity e = new FreezerEntity();
                    e.setUser(userRepository.findByUuid(userUuid).orElseThrow());
                    e.setIngredient(ingredientRepository.findByUuid(request.getIngredientUuid()).orElseThrow());
                    e.setMeasureUnit(measureUnitRepository.findByUuid(request.getMeasureUnitUuid()).orElseThrow());
                    e.setCount(BigDecimal.ZERO);
                    return e;
                });

        entity.setCount(entity.getCount().add(request.getCount()));
        repository.save(entity);
    }

    public void subtractIngredient(FreezerChangeRequest request) {
        UUID userUuid = userUuidProvider.provide();

        FreezerEntity entity = repository
                .findByUser_UuidAndIngredient_UuidAndMeasureUnit_Uuid(
                        userUuid,
                        request.getIngredientUuid(),
                        request.getMeasureUnitUuid()
                )
                .orElseThrow(() ->
                        new IllegalArgumentException("Ingredient not found in freezer")
                );

        BigDecimal newCount = entity.getCount().subtract(request.getCount());

        if (newCount.compareTo(BigDecimal.ZERO) <= 0) {
            repository.delete(entity);
        } else {
            entity.setCount(newCount);
        }
    }

    @Transactional(readOnly = true)
    public List<Freezer> getUserFreezer() {
        return freezerRepository.findAllByUser_Uuid(userUuidProvider.provide()).stream()
                .map(mapper::toDomain)
                .toList();
    }

}


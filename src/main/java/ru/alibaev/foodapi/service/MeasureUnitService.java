package ru.alibaev.foodapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alibaev.foodapi.mapper.MeasureUnitMapper;
import ru.alibaev.foodapi.model.domain.MeasureUnit;
import ru.alibaev.foodapi.model.domain.MeasureUnitForCount;
import ru.alibaev.foodapi.model.entity.MeasureUnitEntity;
import ru.alibaev.foodapi.repository.MeasureUnitRepository;
import ru.alibaev.foodapi.util.MeasureUnitResolver;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MeasureUnitService {

    private final MeasureUnitRepository repository;
    private final MeasureUnitMapper mapper;

    public void create(MeasureUnit unit) {
        repository.save(mapper.toEntity(unit));
    }

    @Transactional(readOnly = true)
    public List<MeasureUnitForCount> getMeasureUnitForIngredientCount(UUID ingredientUuid, BigDecimal count) {
        List<MeasureUnitEntity> measureUnitEntities = repository.findAllByIngredientUuid(ingredientUuid);
        return measureUnitEntities.stream()
                .map(entity -> {
                    MeasureUnitForCount result = new MeasureUnitForCount();
                    result.setCount(count);
                    result.setMeasureUnit(MeasureUnitResolver.resolveMeasureUnitName(entity, count));
                    result.setUuid(entity.getUuid());
                    return result;
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public List<MeasureUnit> getAll() {
        return repository.findAllByDeletedAtIsNull().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

}


package ru.alibaev.foodapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.alibaev.foodapi.model.domain.Step;
import ru.alibaev.foodapi.model.dto.request.StepRequest;
import ru.alibaev.foodapi.model.dto.response.StepResponse;
import ru.alibaev.foodapi.model.entity.StepEntity;

@Mapper(uses = {RecipeMapper.class, ImageMapper.class})
public interface StepMapper {

    Step toDomain(StepEntity entity);

    @Mapping(target = "recipe", ignore = true)
    StepEntity toEntity(Step domain);

    StepResponse toResponse(Step domain);

    Step toDomain(StepRequest request);
}


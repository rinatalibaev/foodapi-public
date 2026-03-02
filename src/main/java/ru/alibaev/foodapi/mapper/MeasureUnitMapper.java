package ru.alibaev.foodapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.alibaev.foodapi.model.domain.MeasureUnit;
import ru.alibaev.foodapi.model.domain.MeasureUnitForCount;
import ru.alibaev.foodapi.model.dto.request.MeasureUnitCreateRequest;
import ru.alibaev.foodapi.model.dto.response.MeasureUnitForCountResponse;
import ru.alibaev.foodapi.model.dto.response.MeasureUnitResponse;
import ru.alibaev.foodapi.model.entity.MeasureUnitEntity;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MeasureUnitMapper {
    MeasureUnit toDomain(MeasureUnitEntity entity);
    MeasureUnitEntity toEntity(MeasureUnit domain);
    MeasureUnit toDomain(MeasureUnitCreateRequest request);
    MeasureUnitResponse toResponse(MeasureUnit domain);
    MeasureUnitForCountResponse toForCountResponse(MeasureUnitForCount domain);
}


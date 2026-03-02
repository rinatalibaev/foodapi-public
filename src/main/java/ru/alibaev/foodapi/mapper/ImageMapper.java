package ru.alibaev.foodapi.mapper;

import org.mapstruct.Mapper;
import ru.alibaev.foodapi.model.domain.Image;
import ru.alibaev.foodapi.model.dto.response.ImageResponse;
import ru.alibaev.foodapi.model.entity.ImageEntity;

@Mapper
public interface ImageMapper {
    Image toDomain(ImageEntity entity);
    ImageEntity toEntity(Image domain);
    ImageResponse toDto(Image domain);
}


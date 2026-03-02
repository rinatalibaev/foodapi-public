package ru.alibaev.foodapi.mapper;

import org.mapstruct.Mapper;
import ru.alibaev.foodapi.model.domain.User;
import ru.alibaev.foodapi.model.dto.response.UserResponse;
import ru.alibaev.foodapi.model.entity.AppUserEntity;

@Mapper(uses = {ImageMapper.class})
public interface UserMapper {

    User toDomain(AppUserEntity entity);

    AppUserEntity toEntity(User domain);

    UserResponse toResponse(User domain);
}


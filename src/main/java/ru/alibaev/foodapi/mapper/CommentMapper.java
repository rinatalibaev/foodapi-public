package ru.alibaev.foodapi.mapper;
import org.mapstruct.Mapper;
import ru.alibaev.foodapi.model.domain.Comment;
import ru.alibaev.foodapi.model.dto.response.CommentResponse;
import ru.alibaev.foodapi.model.entity.CommentEntity;

@Mapper(uses = {UserMapper.class, ImageMapper.class})
public interface CommentMapper {

    Comment toDomain(CommentEntity entity);
    CommentEntity toEntity(Comment domain);
    CommentResponse toResponse(Comment domain);
}

package com.corporation.mapper;

import com.corporation.dto.CommentDto;
import com.corporation.model.Comment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "user", target ="user")
    @Mapping(source = "post", target ="post")
    CommentDto toDto(Comment comment);

    @Mapping(source = "user", target ="user")
    @Mapping(source = "post", target ="post")
    Comment toEntity(CommentDto commentDto);
}

package com.corporation.mapper;

import com.corporation.dto.PostDto;
import com.corporation.model.Post;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "user.id", target = "userId")
    PostDto toDto(Post post);

    @Mapping(target = "user", ignore = true)
    Post toEntity(PostDto postDto);

    @Mapping(target = "user", ignore = true)
    void updateFromDto(PostDto postDto, @MappingTarget Post post);
}
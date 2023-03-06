package com.corporation.mapper;

import com.corporation.dto.PostDto;
import com.corporation.model.Post;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface PostMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "project.id", target = "projectId")
    PostDto toDto(Post post);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "postStatistics", ignore = true)
    Post toEntity(PostDto postDto);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "postStatistics", ignore = true)
    void updateFromDto(PostDto postDto, @MappingTarget Post post);
}
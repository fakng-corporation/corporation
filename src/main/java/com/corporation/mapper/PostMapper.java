package com.corporation.mapper;

import com.corporation.dto.PostDto;
import com.corporation.model.Post;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface PostMapper {
    PostDto toDto(Post post);

    Post toEntity(PostDto postDto);
}

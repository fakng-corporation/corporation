package com.corporation.mapper;

import com.corporation.dto.PostDto;
import com.corporation.model.Post;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface PostMapper {
    @Mapping(source = "user.id", target = "userId")
    PostDto toDto(Post post);

    @Mapping(target = "user", ignore = true)
    Post toEntity(PostDto postDto);
}

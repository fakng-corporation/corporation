package com.corporation.mapper;

import com.corporation.dto.PostDto;
import com.corporation.dto.UpdateDraftPostDto;
import com.corporation.model.Post;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface PostMapper {
    @Mapping(source = "user.id", target = "userId")
    PostDto toDto(Post post);

    @Mapping(target = "user", ignore = true)
    Post toEntity(PostDto postDto);

    @Mappings({
            @Mapping(target = "published", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true),
            @Mapping(target = "publishedAt", ignore = true)}
    )
    void updateDraftToEntity(UpdateDraftPostDto updateDraftPostDto, @MappingTarget Post post);
}

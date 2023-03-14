package com.corporation.mapper;

import com.corporation.dto.PostStatisticsDto;
import com.corporation.model.PostStatistics;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostStatisticsMapper {
    @Mapping(source = "post.id", target = "postId")
    PostStatisticsDto toDto(PostStatistics postStatistics);

    @Mapping(target = "post", ignore = true)
    PostStatistics toEntity(PostStatisticsDto postStatisticsDto);
}

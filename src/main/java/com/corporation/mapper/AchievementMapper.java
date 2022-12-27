package com.corporation.mapper;

import com.corporation.dto.AchievementDto;
import com.corporation.model.Achievement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AchievementMapper {

    @Mapping(target = "project", ignore = true)
    Achievement toEntity(AchievementDto achievementDto);

    @Mapping(source = "project.id", target = "projectId")
    AchievementDto toDto(Achievement achievement);

    @Mapping(target = "project", ignore = true)
    void updateFromDto(AchievementDto achievementDto, @MappingTarget Achievement achievement);
}

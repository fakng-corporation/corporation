package com.corporation.mapper;

import com.corporation.dto.ProjectDto;
import com.corporation.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "owner", ignore = true)
    Project toEntity(ProjectDto projectDto);

    @Mapping(target = "ownerId", ignore = true)
    ProjectDto toDto(Project project);
}

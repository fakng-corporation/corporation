package com.corporation.mapper;

import com.corporation.dto.ProjectDto;
import com.corporation.model.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    Project toEntity(ProjectDto projectDto);

    ProjectDto toDto(Project project);

}

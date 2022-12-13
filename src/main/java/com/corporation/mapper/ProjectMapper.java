package com.corporation.mapper;

import com.corporation.dto.ProjectDto;
import com.corporation.model.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {

    public abstract Project projectFromProjectDto(ProjectDto projectDto);

}

package com.corporation.mapper;

import com.corporation.dto.ProjectDto;
import com.corporation.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "teams", ignore = true)
    @Mapping(target = "achievements", ignore = true)
    @Mapping(target = "owner", ignore = true)
    Project toEntity(ProjectDto projectDto);

    @Mapping(source = "owner.id", target = "ownerId")
    ProjectDto toDto(Project project);

    @Mapping(target = "teams", ignore = true)
    @Mapping(target = "achievements", ignore = true)
    @Mapping(target = "owner", ignore = true)
    void updateFromDto(ProjectDto projectDto, @MappingTarget Project project);

}

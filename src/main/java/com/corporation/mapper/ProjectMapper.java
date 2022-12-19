package com.corporation.mapper;

import com.corporation.dto.ProjectDto;
import com.corporation.model.Project;
import com.corporation.model.User;
import com.corporation.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {

    @Autowired
    private UserService userService;

    @Mapping(source = "ownerId", target = "owner", qualifiedByName = "getOwnerById")
    public abstract Project toEntity(ProjectDto projectDto);

    @Mapping(source = "owner.id", target = "ownerId")
    public abstract ProjectDto toDto(Project project);

    @Mapping(source = "ownerId", target = "owner", qualifiedByName = "getOwnerById")
    public abstract void updateFromDto(ProjectDto projectDto, @MappingTarget Project project);

    @Named("getOwnerById")
    User getOwnerById(long id) {
        return userService.findById(id);
    }
}

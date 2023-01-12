package com.corporation.mapper;

import com.corporation.dto.RoleDto;
import com.corporation.model.Role;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface RoleMapper {

    @Mapping(source = "project.id", target = "projectId")
    RoleDto toDto(Role role);

    @Mapping(target = "project", ignore = true)
    Role toEntity(RoleDto roleDto);

    @Mapping(target = "project", ignore = true)
    void updateFromDto(RoleDto roleDto, @MappingTarget Role role);
}

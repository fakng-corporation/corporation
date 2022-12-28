package com.corporation.mapper;

import com.corporation.dto.TeamDto;
import com.corporation.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(target = "project", ignore = true)
    Team toEntity(TeamDto teamDto);

    @Mapping(source = "project.id", target = "projectId")
    TeamDto toDto(Team team);

    @Mapping(target = "project", ignore = true)
    void updateFromDto(TeamDto teamDto, @MappingTarget Team team);
}

package com.corporation.controller;

import com.corporation.controller.api.ProjectApi;
import com.corporation.dto.ProjectDto;
import com.corporation.mapper.ProjectMapper;
import com.corporation.model.Project;
import com.corporation.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProjectController implements ProjectApi {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public ProjectDto addProject(ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        project = projectService.save(project);
        return projectMapper.toDto(project);
    }

    @Override
    public ProjectDto updateProject(Long id, ProjectDto projectDto) {
        Project project = projectService.update(id, projectDto);
        return projectMapper.toDto(project);
    }
}

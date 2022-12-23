package com.corporation.controller;

import com.corporation.controller.api.ProjectApi;
import com.corporation.dto.ProjectDto;
import com.corporation.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProjectController implements ProjectApi {

    private final ProjectService projectService;

    public ProjectDto addProject(ProjectDto projectDto) {
        return projectService.add(projectDto);
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        return projectService.update(projectDto);
    }

    @Override
    public void deleteProject(Long id) {
        projectService.delete(id);
    }

    @Override
    public List<ProjectDto> getProjects(String keyword, int pageNumber, int pageSize) {
        return projectService.getProjectsByTitle(keyword, pageNumber, pageSize);
    }
}

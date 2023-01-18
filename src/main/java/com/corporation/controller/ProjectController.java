package com.corporation.controller;

import com.corporation.controller.api.ProjectApi;
import com.corporation.dto.ProjectDto;
import com.corporation.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

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
    public Page<ProjectDto> getProjects(String keyword, int pageNumber, int pageSize) {
        return projectService.getProjectsByTitle(keyword, pageNumber, pageSize);
    }

    @Override
    public long projectFollowersAmount(long projectId) {
        return projectService.projectFollowersAmount(projectId);
    }
}
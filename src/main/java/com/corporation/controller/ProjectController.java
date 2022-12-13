package com.corporation.controller;

import com.corporation.dto.ProjectDto;
import com.corporation.mapper.ProjectMapper;
import com.corporation.model.Project;
import com.corporation.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @PostMapping(value = "/add", produces = "application/json")
    public ResponseEntity<?> addProject (@RequestBody ProjectDto projectDto) {
        if (projectService.existByTitle(projectDto.getTitle())) {
            return ResponseEntity.badRequest().body("The specified project title is already in use");
        }
        Project project = projectMapper.projectFromProjectDto(projectDto);
        projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body("Project created");
    }
}

package com.corporation.controller;

import com.corporation.dto.ProjectDto;
import com.corporation.exception.NotUniqueProjectException;
import com.corporation.mapper.ProjectMapper;
import com.corporation.model.Project;
import com.corporation.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @PutMapping
    public ResponseEntity<?> addProject(@RequestBody ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        try {
            project = projectService.save(project);
        } catch (NotUniqueProjectException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(projectMapper.toDto(project));
    }
}

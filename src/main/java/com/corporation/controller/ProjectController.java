package com.corporation.controller;

import com.corporation.dto.ProjectDto;
import com.corporation.mapper.ProjectMapper;
import com.corporation.model.Project;
import com.corporation.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/project")
@Tag(name = "Управление проектами пользователей", description = "Создание, поиск, модификация проектов")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @Operation(
            summary = "Регистрация нового проекта",
            description = "Позволяет создать новый проект"
    )
    @PutMapping
    public ProjectDto addProject(@RequestBody ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        project = projectService.save(project);
        return projectMapper.toDto(project);
    }
}

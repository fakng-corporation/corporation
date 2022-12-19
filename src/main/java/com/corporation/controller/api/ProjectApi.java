package com.corporation.controller.api;

import com.corporation.dto.ProjectDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/project")
@Tag(name = "Управление проектами пользователей", description = "Создание, поиск, модификация проектов")
public interface ProjectApi {

    @Operation(summary = "Регистрация нового проекта", description = "Позволяет создать новый проект")
    @PutMapping
    ProjectDto addProject(@RequestBody ProjectDto projectDto);

    @Operation(summary = "Изменение проекта", description = "Позволяет внести изменения в проект")
    @PostMapping
    ProjectDto updateProject(@RequestBody ProjectDto projectDto);
}

package com.corporation.controller.api;

import com.corporation.dto.ProjectDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@RequestMapping("/project")
@Tag(name = "Управление проектами пользователей", description = "Создание, поиск, модификация проектов")
public interface ProjectApi {

    @Operation(summary = "Регистрация нового проекта", description = "Позволяет создать новый проект")
    @PutMapping
    ProjectDto addProject(@RequestBody ProjectDto projectDto, Principal principal);

    @Operation(summary = "Изменение проекта", description = "Позволяет внести изменения в проект")
    @PostMapping
    ProjectDto updateProject(@RequestBody ProjectDto projectDto);

    @Operation(summary = "Удаление проекта", description = "Позволяет удалить проект по Id")
    @DeleteMapping("/{id}")
    void deleteProject(@PathVariable("id") Long id);

    @Operation(summary = "Получение списка проектов", description = "Позволяет получать проекты по заданной маске")
    @GetMapping
    Page<ProjectDto> getProjects(
            @RequestParam(value = "Маска поиска", defaultValue = "") String keyword,
            @RequestParam(value = "Номер страницы",defaultValue = "0") int pageNumber,
            @RequestParam(value = "Элементов на странице",defaultValue = "10") int pageSize);
}

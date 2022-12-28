package com.corporation.controller.api;

import com.corporation.dto.TeamDto;
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

@RequestMapping("/team")
@Tag(name = "Управление командами проекта", description = "Создание, поиск, модификация команд")
public interface TeamApi {

    @Operation(summary = "Создание новой команды", description = "Позволяет создать новую команду в проекте")
    @PutMapping
    TeamDto addTeam(@RequestBody TeamDto teamDto);

    @Operation(summary = "Изменение команды", description = "Позволяет изменить атрибуты команды в проекте")
    @PostMapping
    TeamDto updateTeam(@RequestBody TeamDto teamDto);

    @Operation(summary = "Удаление команды", description = "Позволяет удалить команду по Id")
    @DeleteMapping("/{id}")
    void deleteTeam(@PathVariable("id") Long id);

    @Operation(summary = "Получение списка команд проекта", description = "Позволяет получать команды по заданной маске")
    @GetMapping
    Page<TeamDto> getTeams(
            @RequestParam(value = "ID проекта", defaultValue = "0") long projectId,
            @RequestParam(value = "Маска поиска", defaultValue = "") String keyword,
            @RequestParam(value = "Номер страницы", defaultValue = "0") int pageNumber,
            @RequestParam(value = "Элементов на странице", defaultValue = "10") int pageSize);
}

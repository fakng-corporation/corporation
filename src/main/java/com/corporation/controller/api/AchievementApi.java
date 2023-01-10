package com.corporation.controller.api;

import com.corporation.dto.AchievementDto;
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

@RequestMapping("/achievement")
@Tag(name = "Управление достижениями", description = "Создание, поиск, модификация достижений")
public interface AchievementApi {

    @Operation(summary = "Создание нового достижения", description = "Позволяет создать новое достижение в проекте")
    @PostMapping
    AchievementDto addAchievement(@RequestBody AchievementDto achievementDto);

    @Operation(summary = "Изменение достижения", description = "Позволяет изменить достижение в проекте")
    @PutMapping
    AchievementDto updateAchievement(@RequestBody AchievementDto achievementDto);

    @Operation(summary = "Удаление достижения", description = "Позволяет удалить достижение по Id")
    @DeleteMapping("/{id}")
    void deleteAchievement(@PathVariable("id") Long id);

    @Operation(summary = "Получение списка достижений проекта", description = "Позволяет получать достижения по заданной маске")
    @GetMapping
    Page<AchievementDto> getAchievements(
            @RequestParam(value = "ID проекта", defaultValue = "0") long projectId,
            @RequestParam(value = "Маска поиска", defaultValue = "") String keyword,
            @RequestParam(value = "Номер страницы", defaultValue = "0") int pageNumber,
            @RequestParam(value = "Элементов на странице", defaultValue = "10") int pageSize);
}

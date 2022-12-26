package com.corporation.controller.api;

import com.corporation.dto.AchievementDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/achievement")
@Tag(name = "Управление достижениями", description = "Создание, поиск, модификация достижений")
public interface AchievementApi {

    @Operation(summary = "Создание нового достижения", description = "Позволяет создать новое достижение в проекте")
    @PutMapping
    AchievementDto addAchievement(@RequestBody AchievementDto achievementDto);
}

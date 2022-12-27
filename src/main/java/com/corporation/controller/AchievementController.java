package com.corporation.controller;

import com.corporation.controller.api.AchievementApi;
import com.corporation.dto.AchievementDto;
import com.corporation.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AchievementController implements AchievementApi {

    private final AchievementService achievementService;

    @Override
    public AchievementDto addAchievement(AchievementDto achievementDto) {
        return achievementService.add(achievementDto);
    }

    @Override
    public AchievementDto updateAchievement(AchievementDto achievementDto) {
        return achievementService.update(achievementDto);
    }
}

package com.corporation.service;

import com.corporation.dto.AchievementDto;
import com.corporation.mapper.AchievementMapper;
import com.corporation.model.Achievement;
import com.corporation.repository.AchievementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AchievementService {

    private final AchievementRepository achievementRepository;
    private final ProjectService projectService;
    private final AchievementMapper achievementMapper;

    public AchievementDto add(AchievementDto achievementDto) {
        Achievement achievement = achievementMapper.toEntity(achievementDto);
        achievement.setProject(projectService.findById(achievementDto.getProjectId()));
        return saveEntityAndReturnDto(achievement);
    }

    private AchievementDto saveEntityAndReturnDto(Achievement achievement) {
        achievement = achievementRepository.save(achievement);
        return achievementMapper.toDto(achievement);
    }
}

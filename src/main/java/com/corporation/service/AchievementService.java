package com.corporation.service;

import com.corporation.dto.AchievementDto;
import com.corporation.mapper.AchievementMapper;
import com.corporation.model.Achievement;
import com.corporation.repository.AchievementRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public AchievementDto update(AchievementDto achievementDto) {
        return achievementRepository.findById(achievementDto.getId())
                .map(achievement -> {
                    achievementMapper.updateFromDto(achievementDto, achievement);
                    return saveEntityAndReturnDto(achievement);
                })
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Achievement with id %d does not exist.", achievementDto.getId())));
    }

    private AchievementDto saveEntityAndReturnDto(Achievement achievement) {
        achievement = achievementRepository.save(achievement);
        return achievementMapper.toDto(achievement);
    }
}

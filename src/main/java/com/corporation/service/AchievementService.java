package com.corporation.service;

import com.corporation.dto.AchievementDto;
import com.corporation.mapper.AchievementMapper;
import com.corporation.model.Achievement;
import com.corporation.model.Project;
import com.corporation.repository.AchievementRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public void delete(Long id) {
        achievementRepository.deleteById(id);
    }

    public Page<AchievementDto> getAchievementsByTitle(long projectId, String keyword, int pageNumber, int pageSize) {
        Project project = projectService.findById(projectId);
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return achievementRepository.findByProjectAndTitleContainingIgnoreCase(project, keyword, page).map(achievementMapper::toDto);
    }

    private AchievementDto saveEntityAndReturnDto(Achievement achievement) {
        achievement = achievementRepository.save(achievement);
        return achievementMapper.toDto(achievement);
    }
}

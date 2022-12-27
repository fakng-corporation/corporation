package com.corporation.service;

import com.corporation.dto.AchievementDto;
import com.corporation.mapper.AchievementMapper;
import com.corporation.model.Achievement;
import com.corporation.model.Project;
import com.corporation.repository.AchievementRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AchievementServiceTest {
    @Mock
    private AchievementRepository achievementRepository;

    @Mock
    private ProjectService projectService;

    @Spy
    @InjectMocks
    private AchievementMapper achievementMapper = Mappers.getMapper(AchievementMapper.class);

    @InjectMocks
    private AchievementService achievementService;

    @Test
    public void shouldReturnCreatedAchievementWithOwner() {
        long achievementId = 10;
        String title = "new achievement";
        long projectId = 5;
        Project project = Project.builder().id(projectId).build();
        Achievement achievement = Achievement.builder().id(achievementId).title(title).project(project).build();
        AchievementDto achievementDto = achievementMapper.toDto(achievement);

        Mockito.when(projectService.findById(projectId))
                .thenReturn(project);
        Mockito.when(achievementRepository.save(achievement))
                .thenReturn(achievement);
        AchievementDto returnedAchievement = achievementService.add(achievementDto);

        Assertions.assertEquals(achievementId, returnedAchievement.getId());
        Assertions.assertEquals(title, returnedAchievement.getTitle());
        Assertions.assertEquals(projectId, returnedAchievement.getProjectId());
    }

    @Test
    public void shouldReturnUpdatedAchievementWithOwner() {
        long achievementId = 10;
        String oldTitle = "old title";
        String newTitle = "new title";
        Achievement achievement = Achievement.builder().id(achievementId).title(oldTitle).build();
        Achievement updatedAchievement = Achievement.builder().id(achievementId).title(newTitle).build();
        AchievementDto achievementDto = achievementMapper.toDto(updatedAchievement);

        Mockito.when(achievementRepository.findById(achievementId))
                .thenReturn(Optional.of(achievement));
        Mockito.when(achievementRepository.save(achievement))
                .thenReturn(updatedAchievement);
        AchievementDto returnedAchievementDto = achievementService.update(achievementDto);

        Assertions.assertEquals(achievementId, returnedAchievementDto.getId());
        Assertions.assertEquals(newTitle, returnedAchievementDto.getTitle());
    }

    @Test
    public void shouldThrowEntityNotFoundException() {

        long id = 100;
        AchievementDto achievementDto = AchievementDto.builder().id(id).build();

        Mockito.when(achievementRepository.findById(any(long.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(
                EntityNotFoundException.class, () -> achievementService.update(achievementDto)
        );
    }
}

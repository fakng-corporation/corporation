package com.corporation.controller;

import com.corporation.dto.AchievementDto;
import com.corporation.model.Achievement;
import com.corporation.service.AchievementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class AchievementControllerTest {

    @Mock
    private AchievementService achievementService;

    @InjectMocks
    private AchievementController achievementController;

    @Test
    public void shouldReturnCreatedAchievementDto() {

        long id = 777;
        String title = "new achievement";

        AchievementDto achievementDto = AchievementDto.builder().title(title).build();
        AchievementDto addedAchievementDto = AchievementDto.builder().id(id).title(title).build();

        Mockito.when(achievementService.add(achievementDto)).thenReturn(addedAchievementDto);

        AchievementDto createdAchievementDto = achievementController.addAchievement(achievementDto);

        Assertions.assertEquals(id, createdAchievementDto.getId());
        Assertions.assertEquals(title, createdAchievementDto.getTitle());
    }

    @Test
    public void shouldReturnUpdatedAchievementDto() {

        long id = 777;
        String newTitle = "new achievement";
        AchievementDto addedAchievementDto = AchievementDto.builder().id(id).title(newTitle).build();

        Mockito.when(achievementService.update(addedAchievementDto)).thenReturn(addedAchievementDto);

        AchievementDto resultAchievementDto = achievementController.updateAchievement(addedAchievementDto);

        Assertions.assertEquals(id, resultAchievementDto.getId());
        Assertions.assertEquals(newTitle, resultAchievementDto.getTitle());
    }

    @Test
    public void shouldDeleteAchievement() {
        Achievement achievement = Achievement.builder().id(any(Long.class)).build();
        achievementController.deleteAchievement(achievement.getId());

        Mockito.verify(achievementService).delete(achievement.getId());
    }

    @Test
    public void shouldReturnAchievementByTitle() {
        long achievementId = 10;
        String title = "title";
        long projectId = 5;
        AchievementDto mockAchievementDto = AchievementDto.builder().id(achievementId).title(title).build();
        List<AchievementDto> achievementDtoList = Collections.singletonList(mockAchievementDto);
        Page<AchievementDto> achievementDtoPage = new PageImpl<>(achievementDtoList);

        Mockito.when(achievementService.getAchievementsByTitle(projectId, title, 0, 5))
                .thenReturn(achievementDtoPage);
        Page<AchievementDto> page = achievementController.getAchievements(projectId, title, 0, 5);

        Assertions.assertEquals(page.getTotalElements(), achievementDtoPage.getTotalElements());
        Assertions.assertEquals(page.getContent(), achievementDtoPage.getContent());
    }
}

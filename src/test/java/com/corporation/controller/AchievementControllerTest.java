package com.corporation.controller;

import com.corporation.dto.AchievementDto;
import com.corporation.mapper.AchievementMapperImpl;
import com.corporation.service.AchievementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AchievementControllerTest {

    @Mock
    private AchievementService achievementService;

    @Spy
    private AchievementMapperImpl achievementMapper;

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
}

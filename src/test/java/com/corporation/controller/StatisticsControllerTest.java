package com.corporation.controller;

import com.corporation.service.StatisticsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StatisticsControllerTest {
    @Mock
    private StatisticsService statisticsService;
    @InjectMocks
    private StatisticsController statisticsController;

    @Test
    public void shouldAddLike() {
        long postId = 10;
        long userId = 1;

        Mockito.doNothing().when(statisticsService).addLike(postId, userId);
        statisticsController.addLike(postId, userId);
        Mockito.verify(statisticsService).addLike(postId, userId);
    }
}

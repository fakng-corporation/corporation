package com.corporation.controller;

import com.corporation.dto.PostStatisticsDto;
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
    public void shouldReturnPostStatistics() {
        long postId = 1;
        PostStatisticsDto postStatsDto = new PostStatisticsDto();

        Mockito.when(statisticsService.getPostStatistics(postId))
                .thenReturn(postStatsDto);

        statisticsController.getPostStatistics(postId);

        Mockito.verify(statisticsService).getPostStatistics(postId);
    }
}

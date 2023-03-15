package com.corporation.service;

import com.corporation.mapper.PostStatisticsMapper;
import com.corporation.model.PostStatistics;
import com.corporation.repository.PostStatsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest {
    @Mock
    private PostStatsRepository postStatsRepository;

    @Mock
    private PostStatisticsMapper postStatisticsMapper;

    @InjectMocks
    private StatisticsService statisticsService;

    @Test
    public void shouldReturnPostStatistics() {
        long postId = 1;
        Optional<PostStatistics> postStats = Optional.of(new PostStatistics());

        Mockito.when(postStatsRepository.findPostStatisticsByPostId(postId))
                .thenReturn(postStats);
        statisticsService.getPostStatistics(postId);

        Mockito.verify(postStatisticsMapper).toDto(postStats.get());
        Assertions.assertNotNull(postStats.get());
    }
}

package com.corporation.service;

import com.corporation.dto.PostStatisticsDto;
import com.corporation.exception.NotFoundEntityException;
import com.corporation.mapper.PostStatisticsMapper;
import com.corporation.model.PostStatistics;
import com.corporation.repository.PostStatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatisticsService {
    private final PostStatsRepository postStatsRepository;
    private final PostStatisticsMapper postStatisticsMapper;

    public PostStatisticsDto getPostStatistics(long postId) {
        PostStatistics postStatistics = postStatsRepository.findPostStatisticsByPostId(postId)
                .orElseThrow(() -> new NotFoundEntityException(
                        String.format("Post with id %d doesn't exists", postId)
                ));
        return postStatisticsMapper.toDto(postStatistics);
    }
}

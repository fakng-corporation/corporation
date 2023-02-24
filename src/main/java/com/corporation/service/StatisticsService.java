package com.corporation.service;

import com.corporation.model.service.Like;
import com.corporation.repository.PostStatsRepository;
import com.corporation.repository.service.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final LikeRepository likeRepository;
    private final PostStatsRepository postStatsRepository;

    public void addLike(Like like) {
        likeRepository.save(like);
        postStatsRepository.addLike(like.getPost().getId());
    }
}

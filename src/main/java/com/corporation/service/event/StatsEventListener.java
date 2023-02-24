package com.corporation.service.event;

import com.corporation.model.PostStatistics;
import com.corporation.repository.PostStatsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatsEventListener {
    private PostStatsRepository postStatsRepository;

    @Transactional
    @EventListener
    public void createPostStatistics(long postId) {
        PostStatistics postStatistics = new PostStatistics();
        postStatistics.setPostId(postId);
        postStatsRepository.save(postStatistics);
    }
}

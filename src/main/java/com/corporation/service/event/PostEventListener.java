package com.corporation.service.event;

import com.corporation.model.PostStatistics;
import com.corporation.repository.PostStatsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostEventListener {
    private PostStatsRepository postStatsRepository;

    @EventListener
    @Transactional
    public void createPostEventListener(PostCreateEvent postEvent) {
        PostStatistics postStatistics = new PostStatistics();
        postStatistics.setPost(postEvent.getPost());

        postStatsRepository.save(postStatistics);
    }
}

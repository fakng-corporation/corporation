package com.corporation.service.event;

import com.corporation.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatsEventPublisher {
    private ApplicationEventPublisher applicationEventPublisher;

    public void createPostStatistics(Post post) {

    }

    public void addLikeToPost(LikeEvent likeEvent) {
        applicationEventPublisher.publishEvent(likeEvent);
    }
}

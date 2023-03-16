package com.corporation.service.event;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeEventPublisher {
    private ApplicationEventPublisher applicationEventPublisher;

    public void addLike(long postId) {
        applicationEventPublisher.publishEvent(new AddLikeEvent(postId));
    }

    public void deleteLike(long postId) {
        applicationEventPublisher.publishEvent(new DeleteLikeEvent(postId));
    }
}

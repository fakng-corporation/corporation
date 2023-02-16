package com.corporation.service.event;

import com.corporation.model.Post;
import com.corporation.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void addLike(Post post, User user) {
        LikeEvent likeEvent = new LikeEvent(post, user);
        applicationEventPublisher.publishEvent(likeEvent);
    }
}

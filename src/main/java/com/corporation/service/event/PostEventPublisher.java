package com.corporation.service.event;

import com.corporation.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostEventPublisher {
    private ApplicationEventPublisher applicationEventPublisher;

    public void createPost(Post post) {
        applicationEventPublisher.publishEvent(new PostCreateEvent(post));
    }
}

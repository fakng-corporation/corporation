package com.corporation.service.event;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatsEventListener {
    @Transactional
    @EventListener
    public void increaseLikesOfPost(LikeEvent likeEvent) {

    }
}

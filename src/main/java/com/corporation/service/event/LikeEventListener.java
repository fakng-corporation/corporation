package com.corporation.service.event;

import com.corporation.model.service.Like;
import com.corporation.service.StatisticsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeEventListener {
    private StatisticsService statisticsService;

    @EventListener
    @Transactional
    public void addLikeEventListener(LikeEvent likeEvent) {
        Like like = Like.builder()
                .post(likeEvent.getPost())
                .user(likeEvent.getUser())
                .build();
        statisticsService.addLike(like);
    }
}

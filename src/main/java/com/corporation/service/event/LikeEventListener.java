package com.corporation.service.event;

import com.corporation.repository.PostStatisticsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeEventListener {
    private final PostStatisticsRepository postStatisticsRepository;

    @EventListener
    @Transactional
    public void addLikeEventListener(AddLikeEvent addLikeEvent) {
        postStatisticsRepository.addLike(addLikeEvent.getPostId());
    }

    @EventListener
    @Transactional
    public void deleteLikeEventListener(DeleteLikeEvent deleteLikeEvent) {
        postStatisticsRepository.deleteLike(deleteLikeEvent.getPostId());
    }
}

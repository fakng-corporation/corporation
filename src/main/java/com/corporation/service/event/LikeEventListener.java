package com.corporation.service.event;

import com.corporation.repository.PostStatsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeEventListener {
    private final PostStatsRepository postStatsRepository;

    @EventListener
    @Transactional
    public void addLikeEventListener(AddLikeEvent likeEvent) {
        postStatsRepository.addLike(likeEvent.getPostId());
    }

    @EventListener
    @Transactional
    public void deleteLikeEventListener(DeleteLikeEvent likeEvent) {
        postStatsRepository.deleteLike(likeEvent.getPostId());
    }
}

package com.corporation.service.event;

import com.corporation.model.service.Like;
import com.corporation.repository.PostStatisticsRepository;
import com.corporation.service.LikeService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeEventListener {
    private LikeService likeService;
    private PostStatisticsRepository postStatisticsRepository;

    @EventListener
    @Transactional
    public void addLikeEventListener(LikeEvent likeEvent) {
        Like like = Like.builder()
                .user(likeEvent.getUser())
                .post(likeEvent.getPost())
                .build();
        likeService.save(like);

        postStatisticsRepository.addLike(likeEvent.getPost().getId());
    }
}

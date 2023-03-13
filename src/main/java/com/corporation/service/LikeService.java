package com.corporation.service;

import com.corporation.model.Post;
import com.corporation.model.User;
import com.corporation.model.service.Like;
import com.corporation.repository.service.LikeRepository;
import com.corporation.service.event.LikeEventPublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final PostService postService;
    private final UserService userService;
    private final LikeRepository likeRepository;
    private final LikeEventPublisher likeEventPublisher;

    @Transactional
    public void addLike(long postId, long userId) {
        Optional<Like> likeFromTable = likeRepository.findByPostIdAndUserId(postId, userId);

        likeFromTable.ifPresentOrElse(like -> {
            likeRepository.delete(like);
            likeEventPublisher.deleteLike(postId);
        }, () -> {
            Post post = postService.findById(postId);
            User user = userService.findById(userId);
            Like like = Like.builder()
                    .post(post)
                    .user(user)
                    .build();
            likeRepository.save(like);

            likeEventPublisher.addLike(postId);
        });
    }
}
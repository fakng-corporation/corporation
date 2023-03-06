package com.corporation.service;

import com.corporation.model.Post;
import com.corporation.model.User;
import com.corporation.model.service.Like;
import com.corporation.repository.service.LikeRepository;
import com.corporation.service.event.LikeEventPublisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest {
    @Mock
    private PostService postService;
    @Mock
    private UserService userService;
    @Mock
    private LikeRepository likeRepository;
    @Mock
    private LikeEventPublisher likeEventPublisher;

    @InjectMocks
    private StatisticsService statisticsService;

    @Test
    public void shouldAddLikeOrDelete() {
        long postId = 10;
        long userId = 1;

        Like mockLike = new Like();
        Optional<Like> mockOptLike = Optional.of(mockLike);

        Mockito.when(likeRepository.findByPostIdAndUserId(postId, userId))
                .thenReturn(mockOptLike);

        statisticsService.addLike(postId, userId);

        Mockito.verify(likeRepository).findByPostIdAndUserId(postId, userId);
        Mockito.verify(likeRepository).delete(mockOptLike.get());
        Mockito.verify(likeEventPublisher).deleteLike(postId);


        mockOptLike = Optional.empty();

        Mockito.when(likeRepository.findByPostIdAndUserId(postId, userId))
                .thenReturn(mockOptLike);

        statisticsService.addLike(postId, userId);

        Post receivedPost = postService.findById(postId);
        User receivedUser = userService.findById(userId);

        mockLike = Like.builder()
                .post(receivedPost)
                .user(receivedUser)
                .build();

        Mockito.verify(likeRepository).save(mockLike);
        Mockito.verify(likeEventPublisher).addLike(postId);
    }
}

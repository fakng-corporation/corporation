package com.corporation.controller;

import com.corporation.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StatisticsControllerTest {
    @Mock
    private PostService postService;

    @InjectMocks
    private StatisticsController statisticsController;

    @Test
    public void shouldAddLikeToPost() {
        long postId = 12;
        long likedItUserId = 32;

        Mockito.doNothing().when(postService)
                .addLike(postId, likedItUserId);

        statisticsController.addLikeToPost(postId, likedItUserId);

        Mockito.verify(postService).addLike(postId, likedItUserId);
    }
}

package com.corporation.controller;

import com.corporation.service.LikeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LikeControllerTest {
    @Mock
    private LikeService likeService;
    @InjectMocks
    private LikeController likeController;

    @Test
    public void shouldAddLike() {
        long postId = 10;
        long userId = 1;

        Mockito.doNothing().when(likeService).addLike(postId, userId);
        likeController.addLike(postId, userId);
        Mockito.verify(likeService).addLike(postId, userId);
    }
}

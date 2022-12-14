package com.corporation.controller;

import com.corporation.mapper.PostMapper;
import com.corporation.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;


class PostControllerTest {

    @Mock
    private PostService postService;

    @Spy
    private PostMapper postMapper;

    @InjectMocks
    private PostController postController;

    @Test
    void createPost() {
    }
}
package com.corporation.service;

import com.corporation.model.Post;
import com.corporation.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    public void shouldReturnCreatedPost() {

        long id = 322;
        String title = "Some Title";
        String body = "Здесь мог быть Ваш код";

        Post mockPost = Post.builder().id(id).title(title).body(body).build();

        Mockito.when(postRepository.findPostByTitle(title)).thenReturn(Optional.empty());

        Mockito.when(postRepository.save(mockPost)).thenReturn(mockPost);

        Post createdPost = postService.save(mockPost);

        Assertions.assertEquals(id, createdPost.getId());
        Assertions.assertEquals(title, createdPost.getTitle());
        Assertions.assertEquals(body, createdPost.getBody());

    }


}
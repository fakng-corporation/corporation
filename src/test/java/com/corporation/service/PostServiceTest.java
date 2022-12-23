package com.corporation.service;

import com.corporation.model.Post;
import com.corporation.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    public void setUp() {
        existingId = 12L;
        nonExistingId = 100L;
    }

    @Test
    public void shouldReturnCreatedPost() {

        long id = 322;
        String title = "Some Title";
        String body = "Здесь мог быть Ваш код";
        boolean isPublished = false;

        Post mockPost = Post.builder().id(id).title(title).body(body).build();

        Mockito.when(postRepository.save(mockPost)).thenReturn(mockPost);

        Post createdPost = postService.savePostDraft(mockPost);

        Assertions.assertEquals(id, createdPost.getId());
        Assertions.assertEquals(title, createdPost.getTitle());
        Assertions.assertEquals(body, createdPost.getBody());
        Assertions.assertEquals(isPublished, createdPost.isPublished());

    }

    @Test
    public void shouldDeleteById() {
        Mockito.doNothing().when(postRepository).deleteById(existingId);
        Assertions.assertDoesNotThrow(() -> postRepository.deleteById(existingId));
        Mockito.verify(postRepository, Mockito.times(1)).deleteById(existingId);
    }
}
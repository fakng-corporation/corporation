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
        boolean isPublished = false;

        Post mockPost = Post.builder().id(id).title(title).body(body).build();

        Mockito.when(postRepository.save(mockPost)).thenReturn(mockPost);

        Post createdPost = postService.savePostDraft(mockPost);

        Assertions.assertEquals(id, createdPost.getId());
        Assertions.assertEquals(title, createdPost.getTitle());
        Assertions.assertEquals(body, createdPost.getBody());
        Assertions.assertEquals(isPublished, createdPost.getIsPublished());

    }


}
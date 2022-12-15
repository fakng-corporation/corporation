package com.corporation.controller;

import com.corporation.dto.PostDto;
import com.corporation.exception.NotUniquePostException;
import com.corporation.mapper.PostMapper;
import com.corporation.mapper.PostMapperImpl;
import com.corporation.model.Post;
import com.corporation.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Mock
    private PostService postService;

    @Spy
    private PostMapperImpl postMapper;

    @InjectMocks
    private PostController postController;

    @Test
    void shouldReturnCreatedPostDtoAndStatus200() {

        long id = 7;
        String title = "Король Тайтлов";
        String description = "Это всё равно никто не читает, чтобы тут не было написано";

        Post post = Post.builder().title(title).description(description).build();
        PostDto postDto = postMapper.toDto(post);
        Post postWithId = Post.builder().id(id).title(title).description(description).build();

        Mockito.when(postService.save(post)).thenReturn(postWithId);

        ResponseEntity<PostDto> responseEntity = postController.createPost(postDto);

        Mockito.verify(postMapper).toEntity(postDto);

        Assertions.assertEquals(200, responseEntity.getStatusCode().value());

        PostDto createdPostDto = responseEntity.getBody();

        Assertions.assertEquals(id, createdPostDto.getId());
        Assertions.assertEquals(title, createdPostDto.getTitle());
        Assertions.assertEquals(description, createdPostDto.getDescription());

    }

    @Test
    public void shouldReturnStatus400() {
        String title = "Это тоже никто не читает";
        Post post = Post.builder().title(title).build();
        PostDto postDto = postMapper.toDto(post);

        Mockito.when(postService.save(post)).thenThrow(NotUniquePostException.class);

        ResponseEntity<PostDto> responseEntity = postController.createPost(postDto);

        Assertions.assertEquals(400, responseEntity.getStatusCode().value());
    }


}
package com.corporation.controller;

import com.corporation.dto.PostDto;
import com.corporation.mapper.PostMapperImpl;
import com.corporation.model.Post;
import com.corporation.model.User;
import com.corporation.service.PostService;
import com.corporation.service.UserService;
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

    @Mock
    private UserService userService;

    @Spy
    private PostMapperImpl postMapper;

    @InjectMocks
    private PostController postController;

    @Test
    void shouldReturnCreatedPostDtoAndStatus200() {

        long id = 7;
        String title = "Король Тайтлов";
        String description = "Это всё равно никто не читает, чтобы тут не было написано";

        int desiredId = 1;
        String nickname = "boba";
        String email = "boba@boba.com";
        String password = "1234";
        String aboutMe = "I am boba!";

        User mockUser = User
                .builder()
                .id(desiredId)
                .nickname(nickname)
                .email(email)
                .password(password)
                .aboutMe(aboutMe)
                .build();

        Post post = Post.builder().title(title).description(description).user(mockUser).build();
        PostDto postDto = postMapper.toDto(post);
        Post postWithId = Post.builder().id(id).title(title).description(description).user(mockUser).build();


        Mockito.when(postService.save(post)).thenReturn(postWithId);

        ResponseEntity<PostDto> responseEntity = postController.createPost(postDto);

        Mockito.verify(postMapper).toEntity(postDto);

        Assertions.assertEquals(200, responseEntity.getStatusCode().value());

        PostDto createdPostDto = responseEntity.getBody();

        Assertions.assertEquals(id, createdPostDto.getId());
        Assertions.assertEquals(title, createdPostDto.getTitle());
        Assertions.assertEquals(description, createdPostDto.getDescription());

    }

}
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
    void shouldReturnCreatedPostDto() {

        long id = 7;
        String title = "Король Тайтлов";
        String body = "Это всё равно никто не читает, чтобы тут не было написано";
        Boolean isPublished = false;

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

        Post post = Post.builder().title(title).body(body).build();
        PostDto postDto = postMapper.toDto(post);
        Post postWithId = Post.builder().id(id).title(title).body(body).user(mockUser).build();

        Mockito.when(postService.savePostDraft(post)).thenReturn(postWithId);

        PostDto createdPostDto = postController.createPost(postDto);

        Mockito.verify(postMapper).toEntity(postDto);

        Assertions.assertEquals(id, createdPostDto.getId());
        Assertions.assertEquals(title, createdPostDto.getTitle());
        Assertions.assertEquals(body, createdPostDto.getBody());
        Assertions.assertEquals(desiredId, createdPostDto.getUserId());
        Assertions.assertEquals(isPublished, createdPostDto.getIsPublished());

    }

}
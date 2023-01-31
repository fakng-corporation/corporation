package com.corporation.controller;

import com.corporation.dto.PostDto;
import com.corporation.mapper.PostMapperImpl;
import com.corporation.model.Post;
import com.corporation.service.PostService;
import com.corporation.service.ProjectService;
import com.corporation.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    @Mock
    private ProjectService projectService;

    @Spy
    private PostMapperImpl postMapper;

    @InjectMocks
    private PostController postController;

    @Test
    public void shouldReturnCreatedPostDto() {
        long desiredPostId = 7;
        boolean isPublished = false;
        String title = "Король Тайтлов";
        String body = "Это всё равно никто не читает, чтобы тут не было написано";
        Long projectId = 2L;
        long userId = 1;

        PostDto mockPostDto = PostDto.builder()
                .id(desiredPostId)
                .title(title)
                .body(body)
                .userId(userId)
                .projectId(projectId)
                .build();

        Mockito.when(postService.savePostDraft(mockPostDto))
                .thenReturn(mockPostDto);

        PostDto receivedPostDto = postController.createPost(mockPostDto);

        Mockito.verify(postService).savePostDraft(mockPostDto);

        Assertions.assertEquals(desiredPostId, receivedPostDto.getId());
        Assertions.assertEquals(title, receivedPostDto.getTitle());
        Assertions.assertEquals(body, receivedPostDto.getBody());
        Assertions.assertEquals(userId, receivedPostDto.getUserId());
        Assertions.assertEquals(projectId, receivedPostDto.getProjectId());
        Assertions.assertEquals(isPublished, receivedPostDto.isPublished());
    }

    @Test
    public void shouldDeleteById() {
        long existingId = 12L;
        Mockito.doNothing().when(postService).deleteById(existingId);
        Assertions.assertDoesNotThrow(() -> postController.deleteById(existingId));
        Mockito.verify(postService, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    public void shouldUpdateById() {
        long existingId = 12L;
        String title = "Title";
        String body = "Body text";
        String updatedTitle = "Post title hes been updated";
        String updatedBody = "Post body has been updated";
        PostDto existingUpdatePostDto = PostDto.builder().id(existingId).title(title).body(body).build();
        Post mockUpdatedPost = Post.builder().id(existingId).title(updatedTitle).body(updatedBody).build();
        PostDto expectedDto = PostDto.builder().id(existingId).title(updatedTitle).body(updatedBody).build();

        Mockito.when(postService.updatePost(existingUpdatePostDto)).thenReturn(mockUpdatedPost);
        Assertions.assertDoesNotThrow(() -> postController.updatePost(existingUpdatePostDto));
        Mockito.verify(postService, Mockito.times(1)).updatePost(existingUpdatePostDto);
        PostDto actualDto = postController.updatePost(existingUpdatePostDto);
        Assertions.assertEquals(expectedDto, actualDto);
    }

    @Test
    public void shouldReturnUserPostsPage() {
        long desiredId = 1;
        int page = 2;
        int pageSize = 5;
        Page<PostDto> newUserPosts = new PageImpl<>(
                List.of(postMapper.toDto(new Post()), postMapper.toDto(new Post()))
        );

        Mockito.when(postService.getUserPostsById(desiredId, page, pageSize))
                .thenReturn(newUserPosts);
        Page<PostDto> receivedPosts = postController.getUserPostsById(desiredId, page, pageSize);

        Mockito.verify(postService).getUserPostsById(desiredId, page, pageSize);

        Assertions.assertEquals(newUserPosts, receivedPosts);
    }
}
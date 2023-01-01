package com.corporation.service;

import com.corporation.dto.UpdateDraftPostDto;
import com.corporation.mapper.PostMapper;
import com.corporation.model.Post;
import com.corporation.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Spy
    private PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    private Long existingId;
    private String title;
    private String body;
    private boolean isPublished;
    private UpdateDraftPostDto mockUpdateDraftPost;
    private Post mockPost;
    private String updatedTitle;
    private String updatedBody;
    private Post mockUpdatedPost;

    @BeforeEach
    public void setUp() {
        existingId = 12L;
        title = "Some Title";
        body = "Здесь мог быть Ваш код";
        isPublished = false;
        updatedTitle = "Post title hes been updated";
        updatedBody = "Post body has been updated";
        mockUpdateDraftPost = UpdateDraftPostDto.builder().id(existingId).title(title).body(body).build();
        mockPost = Post.builder().id(existingId).title(title).body(body).published(false).build();
        mockUpdatedPost = Post.builder().id(existingId).title(updatedTitle).body(updatedBody).build();
    }

    @Test
    public void shouldReturnCreatedPost() {

        long id = 322;

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
        Assertions.assertDoesNotThrow(() -> postService.deleteById(existingId));
        Mockito.verify(postRepository, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    public void shouldUpdateDraftPost() {
        Mockito.doReturn(Optional.of(mockPost)).when(postRepository).findById(existingId);
        Mockito.doReturn(mockUpdatedPost).when(postRepository).save(mockPost);
        Post updatedPost = postService.updateDraftPost(mockUpdateDraftPost);
        Assertions.assertDoesNotThrow(() -> postService.updateDraftPost(mockUpdateDraftPost));
        Assertions.assertEquals(updatedPost.getTitle(), updatedTitle);
        Assertions.assertEquals(updatedPost.getBody(), updatedBody);
        Assertions.assertEquals(updatedPost.isPublished(), false);
    }
}
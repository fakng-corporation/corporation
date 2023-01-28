package com.corporation.service;

import com.corporation.dto.PostDto;
import com.corporation.exception.NotFoundEntityException;
import com.corporation.mapper.PostMapper;
import com.corporation.model.Post;
import com.corporation.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Spy
    private PostMapper postMapper = Mappers.getMapper(PostMapper.class);

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
        long existingId = 12L;

        Mockito.doNothing().when(postRepository).deleteById(existingId);
        Assertions.assertDoesNotThrow(() -> postService.deleteById(existingId));
        Mockito.verify(postRepository, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    public void shouldUpdateDraftPost() {
        long existingId = 12L;
        String title = "Title";
        String body = "Body text";
        String updatedTitle = "Post title has been updated";
        String updatedBody = "Post body has been updated";
        Post mockPost = Post.builder().id(existingId).title(title).body(body).published(false).build();
        PostDto existingUpdatePostDto = PostDto.builder().id(existingId).title(title).body(body).build();
        Post mockUpdatedPost = Post.builder().id(existingId).title(updatedTitle).body(updatedBody).build();

        Mockito.doReturn(Optional.of(mockPost)).when(postRepository).findById(existingId);
        Mockito.doReturn(mockUpdatedPost).when(postRepository).save(mockPost);
        Post updatedPost = postService.updatePost(existingUpdatePostDto);
        Assertions.assertDoesNotThrow(() -> postService.updatePost(existingUpdatePostDto));
        Assertions.assertEquals(updatedTitle, updatedPost.getTitle());
        Assertions.assertEquals(updatedBody, updatedPost.getBody());
        Assertions.assertEquals(false, updatedPost.isPublished());
    }

    @Test
    public void shouldThrowExceptionOnUpdate() {
        long notExistingId = 12L;
        String title = "Title";
        String body = "Body text";
        PostDto notExistingUpdatePostDto = PostDto.builder().id(notExistingId).title(title).body(body).build();

        Mockito.when(postRepository.findById(notExistingUpdatePostDto.getId())).thenThrow(new NotFoundEntityException(
                String.format("Post %d does not exist", notExistingUpdatePostDto.getId())
        ));
        Assertions.assertThrows(NotFoundEntityException.class, () -> postService.updatePost(notExistingUpdatePostDto));
    }

    @Test
    public void shouldReturnUserPostsPage() {
        long desireId = 1;
        int page = 2;
        int pageSize = 5;
        Page<Post> newUserPosts = new PageImpl<>(
                List.of(new Post(), new Post(), new Post())
        );
        Pageable pageable = PageRequest.of(page, pageSize);

        Mockito.when(postRepository.getUserPostsById(desireId, pageable))
                .thenReturn(newUserPosts);
        Page<PostDto> receivedPosts = postService.getUserPostsById(desireId, page, pageSize);

        Assertions.assertEquals(newUserPosts.map(postMapper::toDto), receivedPosts);
    }
}
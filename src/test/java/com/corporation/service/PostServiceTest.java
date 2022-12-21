package com.corporation.service;

import com.corporation.model.Post;
import com.corporation.model.User;
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

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private Long existingId;
    private Long nonExistingId;
    private User mockUser;
    private Post mockPost;

    @BeforeEach
    public void setUp () {
        existingId = 12L;
        nonExistingId = 100L;
        mockUser = User
                .builder()
                .id(existingId)
                .nickname("jonnyrocket")
                .email("jonnyrocket@yahoo.com")
                .password("qwerty123")
                .aboutMe("My name is John Johnson, Im come from Wisconsin")
                .build();

        mockPost = Post.builder()
                .id(1001L)
                .title("Post Title")
                .body("Post Body")
                .createdAt(LocalDateTime.now())
                .publishedAt(LocalDateTime.now())
                .is_published(true)
                .user(mockUser)
                .build();
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
    public void deleteByIdTestPositive () {
        Mockito.doNothing().when(postRepository).deleteById(existingId);
        Assertions.assertDoesNotThrow(() -> postRepository.deleteById(existingId));
        Mockito.verify(postRepository, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    public void deleteByIdTestNegative () {
        Mockito.doThrow(EmptyResultDataAccessException.class).when(postRepository).deleteById(nonExistingId);
        Assertions.assertThrows(EmptyResultDataAccessException.class, (() -> postService.deleteById(nonExistingId)));
        Mockito.verify(postRepository, Mockito.times(1)).deleteById(nonExistingId);
    }
}
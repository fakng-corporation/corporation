package com.corporation.controller;

import com.corporation.dto.CommentDto;
import com.corporation.dto.PostDto;
import com.corporation.dto.UserDto;
import com.corporation.repository.CommentRepository;
import com.corporation.service.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class CommentControllerTest {

    private UserDto user1;
    private UserDto user2;
    private PostDto mockPostDto;
    private PostDto mockPostDto1;
    private CommentDto commentDto1;
    private CommentDto commentDto2;


    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        commentService = new CommentService(commentRepository);
        user1 = UserDto
                .builder()
                .id(1L)
                .aboutMe("Foo godhood")
                .avatarUrl("disused")
                .email("gerg@yt.ru")
                .nickname("georg999")
                .build();

        user2 = UserDto
                .builder()
                .id(2L)
                .aboutMe("Doo godhood")
                .avatarUrl("vanished")
                .email("redg@fox.ru")
                .nickname("cherrydevil")
                .build();

        long postId = 1;
        String title = "Some Title";
        String body = "Здесь мог быть Ваш код";
        boolean isPublished = false;

        LocalDateTime createdTime = LocalDateTime.now();

        mockPostDto = PostDto
                .builder()
                .id(postId)
                .title(title)
                .body(body)
                .published(isPublished)
                .userId(user1.getId())
                .createdAt(createdTime)
                .build();


        mockPostDto = PostDto
                .builder()
                .id(2L)
                .title(title)
                .body(body)
                .published(isPublished)
                .userId(user2.getId())
                .createdAt(createdTime)
                .build();


        commentDto1 = CommentDto
                .builder()
                .id(1L)
                .user(user2)
                .post(mockPostDto)
                .createdDate(LocalDateTime.now())
                .content("the best article i have ever read")
                .build();
        commentDto2 = CommentDto
                .builder()
                .id(2L)
                .user(user2)
                .post(mockPostDto)
                .createdDate(LocalDateTime.now())
                .content("t ever read")
                .build();
    }


    @Test
    void shouldReturnCommentDto() {
        CommentDto expected = commentDto1;
        Mockito.when(commentController.addComment(expected)).thenReturn(commentDto1);
        CommentDto actual = commentController.addComment(commentDto1);

        Assertions.assertEquals(expected.getPost(), actual.getPost());
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getUser(), actual.getUser());
    }

    @Test
    void shouldReturnCommentsByPostId() {
        List<CommentDto> expected = new ArrayList<>();
        expected.add(commentDto1);
        expected.add(commentDto2);

        Mockito.when(commentController.getCommentByPostId(mockPostDto.getId())).thenReturn(expected);
        List<CommentDto> actual = commentController.getCommentByPostId(mockPostDto.getId());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldNotReturnCommentsByPostId() {
        List<CommentDto> expected = new ArrayList<>();

        Mockito.when(commentController.getCommentByPostId(mockPostDto.getId())).thenReturn(expected);
        List<CommentDto> actual = commentController.getCommentByPostId(mockPostDto.getId());

        Assertions.assertEquals(expected, actual);
    }

}
package com.corporation.service;

import com.corporation.dto.CommentDto;
import com.corporation.dto.PostDto;
import com.corporation.dto.UserDto;
import com.corporation.mapper.CommentMapper;
import com.corporation.model.Comment;
import com.corporation.repository.CommentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


class CommentServiceTest {

    private UserDto user1;
    private UserDto user2;
    private PostDto mockPostDto;
    private CommentDto commentDto1;
    private CommentDto commentDto2;

    @Mock
    private CommentRepository commentRepository;

    private CommentService commentService;


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
    void shouldReturnComment() {
        Comment actual = CommentMapper.INSTANCE.toEntity(commentService.addComment(commentDto1));
        Comment expected = CommentMapper.INSTANCE.toEntity(commentDto1);

        Assertions.assertEquals(expected.getUser().getId(), actual.getUser().getId());
        Assertions.assertEquals(expected.getPost().getId(), actual.getPost().getId());
        Assertions.assertEquals(expected.getContent(), actual.getContent());
    }

    @Test
    void shouldReturnCommentDTOsByPostId() {
        List<Comment> actual = new ArrayList<>(List.of(
                CommentMapper.INSTANCE.toEntity(commentDto1),
                CommentMapper.INSTANCE.toEntity(commentDto2)
        ));

        Mockito.when(commentRepository.getPostCommentByPostId(mockPostDto.getId()))
                .thenReturn(actual);

        List<CommentDto> expected =  commentService.getCommentByPostId(1L);

        Assertions.assertEquals(actual.stream().map(CommentMapper.INSTANCE::toDto).collect(Collectors.toList()), expected);
    }

    @Test
    public void shouldDeleteById() {
        long existingId = 1L;

        Mockito.doNothing().when(commentRepository).deleteById(existingId);
        Assertions.assertDoesNotThrow(() -> commentService.deleteCommentById(existingId));
        Mockito.verify(commentRepository, Mockito.times(1)).deleteById(existingId);
    }

}
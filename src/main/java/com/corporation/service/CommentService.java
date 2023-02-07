package com.corporation.service;

import com.corporation.dto.CommentDto;
import com.corporation.mapper.CommentMapper;
import com.corporation.mapper.PostMapper;
import com.corporation.mapper.UserMapper;
import com.corporation.model.Comment;
import com.corporation.model.Post;
import com.corporation.model.User;
import com.corporation.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentDto addComment(CommentDto commentDto) {
        Comment comment = new Comment();

        User user =  UserMapper.INSTANCE.toEntity(commentDto.getUser());
        Post post = PostMapper.INSTANCE.toEntity(commentDto.getPost());

        comment.setCreatedDate(commentDto.getCreatedDate());
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(commentDto.getContent());
        commentRepository.save(comment);
        return commentToDTO(comment);
    }


    public List<CommentDto> getCommentByPostId(long postId) {
        return commentRepository.getPostCommentByPostId(postId).stream()
                .map(CommentMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    private CommentDto commentToDTO(Comment comment) {
        return CommentMapper.INSTANCE.toDto(comment);
    }

}

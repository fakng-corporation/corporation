package com.corporation.controller;


import com.corporation.dto.CommentDto;
import com.corporation.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;


    @PostMapping("")
    public CommentDto createPostComment(@RequestBody CommentDto commentDto) {
        return commentService.addComment(commentDto);
    }

    @GetMapping("/post/{id}")
    public List<CommentDto> getPostCommentByPostId(
            @PathVariable long id) {
        return commentService.getCommentByPostId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCommentById(@PathVariable long id) {
        commentService.deleteCommentById(id);
    }



}

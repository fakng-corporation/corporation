package com.corporation.controller;

import com.corporation.dto.PostDto;
import com.corporation.mapper.PostMapper;
import com.corporation.model.Post;
import com.corporation.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        post = postService.save(post);
        return ResponseEntity.ok(postMapper.toDto(post));
    }

}

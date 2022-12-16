package com.corporation.controller;

import com.corporation.dto.PostDto;
import com.corporation.mapper.PostMapper;
import com.corporation.model.Post;
import com.corporation.model.User;
import com.corporation.service.PostService;
import com.corporation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final PostMapper postMapper;


    @PostMapping
    public PostDto createPost(@RequestBody PostDto postDto) {
        Optional<User> userOptional = userService.findById(postDto.getUserId());
        Post post = postMapper.toEntity(postDto);
        userOptional.ifPresent(post::setUser);
        post = postService.save(post);
        return postMapper.toDto(post);
    }

}

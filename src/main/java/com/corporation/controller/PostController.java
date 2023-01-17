package com.corporation.controller;

import com.corporation.dto.PostDto;
import com.corporation.mapper.PostMapper;
import com.corporation.model.Post;
import com.corporation.model.User;
import com.corporation.service.PostService;
import com.corporation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final PostMapper postMapper;


    @PostMapping
    public PostDto createPost(@RequestBody PostDto postDto) {
        User user = userService.findById(postDto.getUserId());
        Post post = postMapper.toEntity(postDto);
        post.setUser(user);
        post = postService.savePostDraft(post);
        PostDto postDtoToReturn = postMapper.toDto(post);
        postDtoToReturn.setUserId(post.getUser().getId());
        return postDtoToReturn;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        postService.deleteById(id);
    }

    @PutMapping
    public PostDto updatePost(@RequestBody PostDto post) {
        return postMapper.toDto(postService.updatePost(post));
    }
}

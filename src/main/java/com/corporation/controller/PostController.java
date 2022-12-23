package com.corporation.controller;

import com.corporation.dto.PostDto;
import com.corporation.mapper.PostMapper;
import com.corporation.model.Post;
import com.corporation.model.User;
import com.corporation.service.PostService;
import com.corporation.service.UserService;
import lombok.RequiredArgsConstructor;
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


    @PutMapping
    public PostDto createPost(@RequestBody PostDto postDto) {
        User user = userService.findById(postDto.getUserId());
        Post post = postMapper.toEntity(postDto);
        post.setUser(user);
        post = postService.savePostDraft(post);
        PostDto postDtoToReturn = postMapper.toDto(post);
        postDtoToReturn.setIsPublished(false);
        postDtoToReturn.setUserId(post.getUser().getId());
        return postDtoToReturn;
    }

}

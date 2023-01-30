package com.corporation.controller;

import com.corporation.dto.PostDto;
import com.corporation.mapper.PostMapper;
import com.corporation.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @PostMapping
    public PostDto createPost(@RequestBody PostDto postDto) {
        return postService.savePostDraft(postDto);
    }

    @PostMapping("/project")
    public PostDto createProjectPost(@RequestBody PostDto postDto) {
        return postService.saveProjectPostDraft(postDto);
    }

    @GetMapping("/user/{id}")
    public Page<PostDto> getUserPostsById(@PathVariable("id") long id,
                                          @RequestParam("page") int page,
                                          @RequestParam("size") int pageSize) {
        return postService.getUserPostsById(id, page, pageSize);
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

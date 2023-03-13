package com.corporation.controller;

import com.corporation.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;

    @PutMapping("/post/{id}")
    public void addLike(@PathVariable("id") long postId,
                        @AuthenticationPrincipal(expression = "id") long userId) {
        likeService.addLike(postId, userId);
    }
}

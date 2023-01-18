package com.corporation.controller;

import com.corporation.model.User;
import com.corporation.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follower")
public class FollowerController {
    private final FollowerService followerService;

    @PutMapping("/follow/project/{id}")
    public void followProject(@PathVariable("id") long followingProjectId,
                       @AuthenticationPrincipal User user) {
        followerService.followProject(followingProjectId, user.getId());
    }
    @PutMapping("/unfollow/project/{id}")
    public void unfollowProject(@PathVariable("id") long followingProjectId,
                       @AuthenticationPrincipal User user) {
        followerService.unfollowProject(followingProjectId, user.getId());
    }
}

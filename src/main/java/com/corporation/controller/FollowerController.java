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
@RequestMapping("/follow")
public class FollowerController {
    private final FollowerService followerService;

    @PutMapping("/project/{projectId}/follow")
    public void followProject(@PathVariable("projectId") long followingProjectId,
                       @AuthenticationPrincipal User user) {
        followerService.followProject(followingProjectId, user.getId());
    }

    @PutMapping("/user/{followeeId}/follow/")
    public void followUser(
            @AuthenticationPrincipal User user,
            @PathVariable("followeeId") long followeeId) {
        followerService.followUser(user.getId(), followeeId);
    }
    @PutMapping("/project/{projectId}/unfollow")
    public void unfollowProject(@PathVariable("projectId") long followingProjectId,
                       @AuthenticationPrincipal User user) {
        followerService.unfollowProject(followingProjectId, user.getId());
    }
}

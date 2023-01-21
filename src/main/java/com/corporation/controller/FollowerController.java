package com.corporation.controller;

import com.corporation.model.User;
import com.corporation.service.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowerController {
    private final FollowerService followerService;

    @PutMapping("/project/{projectId}")
    void followProject(@PathVariable("projectId") long followingProjectId,
                       @AuthenticationPrincipal User user) {
        followerService.followProject(followingProjectId, user.getId());
    }
    @GetMapping("/project/findSubscribers")
    public Page<User> getUsersByProjectIdAndNicknameValue(
            @RequestParam("projectId") long projectId,
            @RequestParam(value = "keyword", defaultValue = "") String keyword,
            @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return followerService.findProjectSubscribers(projectId, keyword, pageNumber, pageSize);
    }
}

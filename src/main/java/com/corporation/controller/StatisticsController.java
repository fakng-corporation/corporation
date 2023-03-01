package com.corporation.controller;

import com.corporation.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stats")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @PutMapping("/post/{id}/like")
    public void addLike(@PathVariable("id") long postId,
                        @AuthenticationPrincipal(expression = "id") long userId) {
        statisticsService.addLike(postId, userId);
    }
}

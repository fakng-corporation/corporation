package com.corporation.controller;

import com.corporation.dto.PostStatisticsDto;
import com.corporation.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stats")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/post/{id}")
    public PostStatisticsDto getPostStatistics(@PathVariable("id") long postId) {
        return statisticsService.getPostStatistics(postId);
    }
}

package com.corporation.controller;

import com.corporation.dto.ReviewDto;
import com.corporation.mapper.ReviewMapper;
import com.corporation.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bleschunov Dmitry
 */
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @PostMapping
    public void createReview(
            @RequestBody ReviewDto reviewDto,
            @RequestParam("author_id") long authorId,
            @RequestParam("user_id") long userId) {
        reviewService.createReview(reviewDto, authorId, userId);
    }

    @GetMapping("/by_user")
    public Page<ReviewDto> getReviewsOfUser(
            @RequestParam("user_id") long userId,
            @RequestParam("page") int page,
            @RequestParam("page_size") int pageSize)
    {
        return reviewService.findByUserId(userId, page, pageSize).map(reviewMapper::toDto);
    }

    @GetMapping("/by_author")
    public Page<ReviewDto> getReviewsOfAuthor(
            @RequestParam("author_id") long userId,
            @RequestParam("page") int page,
            @RequestParam("page_size") int pageSize)
    {
        return reviewService.findByAuthorId(userId, page, pageSize).map(reviewMapper::toDto);
    }
}

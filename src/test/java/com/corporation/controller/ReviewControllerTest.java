package com.corporation.controller;

import com.corporation.dto.ReviewDto;
import com.corporation.mapper.ReviewMapper;
import com.corporation.model.Review;
import com.corporation.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {

    @Spy
    private ReviewMapper reviewMapper;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    @Test
    public void shouldCreateReview() {
        ReviewDto reviewDto = ReviewDto.builder().build();
        long authorId = 0;
        long userId = 10;

        reviewController.createReview(reviewDto, authorId, userId);

        Mockito.verify(reviewService).createReview(reviewDto, authorId, userId);
    }

    @Test
    public void shouldReturnPageReviewDtoByUserId() {
        long userId = 5;
        int page = 0;
        int pageSize = 10;
        Page<Review> pageObject = Page.empty();
        Mockito.when(reviewService.findByUserId(userId, page, pageSize))
                        .thenReturn(pageObject);

        reviewController.getReviewsOfUser(userId, page, pageSize);

        Mockito.verify(reviewService).findByUserId(userId, page, pageSize);
    }

    @Test
    public void shouldReturnPageReviewDtoByAuthorId() {
        long authorId = 5;
        int page = 0;
        int pageSize = 10;
        Page<Review> pageObject = Page.empty();
        Mockito.when(reviewService.findByAuthorId(authorId, page, pageSize))
                .thenReturn(pageObject);

        reviewController.getReviewsOfAuthor(authorId, page, pageSize);

        Mockito.verify(reviewService).findByAuthorId(authorId, page, pageSize);
    }



}

package com.corporation.service;

import com.corporation.dto.ReviewDto;
import com.corporation.mapper.ReviewMapperImpl;
import com.corporation.model.Review;
import com.corporation.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Spy
    private ReviewMapperImpl reviewMapper;

    @Mock
    private UserService userService;

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    public void shouldCreateReview() {
        ReviewDto reviewDto = ReviewDto.builder().build();
        long authorId = 0;
        long userId = 10;

        reviewService.createReview(reviewDto, authorId, userId);

        Mockito.verify(reviewRepository).save(Mockito.any(Review.class));
    }

    @Test
    public void shouldReturnPageReviewByUserId() {
        long userId = 5;
        int page = 0;
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Review> pageObject = Page.empty();
        Mockito.when(reviewRepository.findByUserId(userId, pageRequest))
                .thenReturn(pageObject);

        reviewService.findByUserId(userId, page, pageSize);

        Mockito.verify(reviewRepository).findByUserId(userId, pageRequest);
    }

    @Test
    public void shouldReturnPageReviewByAuthorId() {
        long authorId = 5;
        int page = 0;
        int pageSize = 10;
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Review> pageObject = Page.empty();
        Mockito.when(reviewRepository.findByAuthorId(authorId, pageRequest))
                .thenReturn(pageObject);

        reviewService.findByAuthorId(authorId, page, pageSize);

        Mockito.verify(reviewRepository).findByAuthorId(authorId, pageRequest);
    }
}

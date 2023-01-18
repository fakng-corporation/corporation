package com.corporation.service;

import com.corporation.dto.ReviewDto;
import com.corporation.mapper.ReviewMapper;
import com.corporation.model.Review;
import com.corporation.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bleschunov Dmitry
 */
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    private final UserService userService;

    public Page<Review> findByUserId(long userId, int page, int pageSize) {
        return reviewRepository.findByUserId(userId, PageRequest.of(page, pageSize));
    }

    public Page<Review> findByAuthorId(long authorId, int page, int pageSize) {
        return reviewRepository.findByAuthorId(authorId, PageRequest.of(page, pageSize));
    }

    @Transactional
    public void createReview(ReviewDto reviewDto, long authorId, long userId) {
        Review review = reviewMapper.toEntity(reviewDto);
        review.setAuthor(userService.findById(authorId));
        review.setUser(userService.findById(userId));
        reviewRepository.save(review);
    }
}

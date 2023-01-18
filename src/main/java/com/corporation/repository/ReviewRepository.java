package com.corporation.repository;

import com.corporation.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bleschunov Dmitry
 */
@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    Page<Review> findByUserId(long userId, Pageable pageable);
    Page<Review> findByAuthorId(long userId, Pageable pageable);
}

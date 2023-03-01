package com.corporation.repository.service;

import com.corporation.model.service.Like;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long> {

    @Query(nativeQuery = true, value = "select * from public.like " +
            "where post_id = :postId and user_id = :userId")
    Optional<Like> findByPostIdAndUserId(long postId, long userId);
}

package com.corporation.repository;

import com.corporation.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    @Query(nativeQuery = true, value = "select p.* from post as p " +
            "join \"user\" as u on p.user_id = u.id " +
            "where u.id = :userId and p.is_published = true")
    Page<Post> getUserPostsById(long userId, Pageable pageable);
}

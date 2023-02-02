package com.corporation.repository;

import com.corporation.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Query(value = "SELECT comments " +
            "FROM comments c LEFT JOIN Post p ON p.id = c.id " +
            "WHERE p.id = : postId", nativeQuery = true)
    List<Comment> getPostCommentByPostId(long postId);
}

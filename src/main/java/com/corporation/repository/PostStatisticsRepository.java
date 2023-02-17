package com.corporation.repository;

import com.corporation.model.PostStatistics;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostStatisticsRepository extends CrudRepository<PostStatistics, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE post_statistics SET likes = likes + 1 " +
            "WHERE post_id = :postId")
    void addLike(long postId);
}

package com.corporation.repository;

import com.corporation.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FollowerRepository extends CrudRepository<User, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO project_followers (project_id, follower_id) VALUES (:projectId, :followerId)")
    void followProject(long projectId, long followerId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM followers WHERE follower_id = :followerId AND followee_id = :followeeId")
    void unfollowUser(long followerId, long followeeId);
}
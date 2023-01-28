package com.corporation.repository;

import com.corporation.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FollowerRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO project_followers (project_id, follower_id) VALUES (:projectId, :followerId)")
    void followProject(long projectId, long followerId);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO followers (follower_id, followee_id) VALUES (:followerId, :followeeId)")
    void followUser(long followerId, long followeeId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM followers WHERE follower_id = :followerId AND followee_id = :followeeId")
    void unfollowUser(long followerId, long followeeId);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM project_followers WHERE project_id= :projectId and follower_id = :followerId")
    void unfollowProject(long projectId, long followerId);

    @Query(nativeQuery = true, value =
            "SELECT u.* FROM \"user\" AS u JOIN project_followers AS pf ON u.id = pf.follower_id WHERE project_id=:projectId AND u.nickname LIKE %:keyword% ORDER BY u.id")
    Page<User> findProjectFollowers(long projectId, String keyword, Pageable page);

    @Query(nativeQuery = true, value =
            "SELECT u.* FROM \"user\" AS u JOIN followers AS f ON u.id = f.follower_id WHERE followee_id = :userId AND u.nickname LIKE %:keyword% ORDER BY u.id")
    Page<User> findUserFollowers(long userId, String keyword, Pageable page);
}

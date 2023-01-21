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
    Page<User> findByFollowingProjectsIdAndNicknameContainingIgnoreCaseOrderById(long projectId, String keyword, Pageable page);
}

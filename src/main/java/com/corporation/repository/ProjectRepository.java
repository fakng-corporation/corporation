package com.corporation.repository;

import com.corporation.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM project_followers WHERE project_id= :projectId")
    long getProjectFollowersAmountByProjectId(long projectId);
}

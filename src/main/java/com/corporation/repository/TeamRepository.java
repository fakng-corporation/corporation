package com.corporation.repository;

import com.corporation.model.Project;
import com.corporation.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Page<Team> findByProjectAndTitleContainingIgnoreCase(Project project, String keyword, Pageable pageable);

    boolean existsByProjectAndTitle(Project project, String title);
}

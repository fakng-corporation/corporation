package com.corporation.repository;

import com.corporation.model.Achievement;
import com.corporation.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends CrudRepository<Achievement, Long> {

    Page<Achievement> findByProjectAndTitleContainingIgnoreCase(Project project, String keyword, Pageable pageable);
}

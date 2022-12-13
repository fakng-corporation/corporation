package com.corporation.service;

import com.corporation.dao.ProjectDao;
import com.corporation.model.Project;
import com.corporation.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectDao projectDao;
    private final ProjectRepository projectRepository;

    @Transactional
    public boolean existByTitle(String title) {
        return projectDao.existByTitle(title);
    }

    public void createProject(Project project) {
        projectRepository.save(project);
    }
}

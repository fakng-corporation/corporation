package com.corporation.service;

import com.corporation.model.Project;
import com.corporation.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project save(Project project) {
        return projectRepository.save(project);
    }
}

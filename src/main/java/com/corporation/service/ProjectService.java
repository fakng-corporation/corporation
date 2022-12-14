package com.corporation.service;

import com.corporation.exception.NotUniqueProjectException;
import com.corporation.model.Project;
import com.corporation.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private Optional<Project> findProjectByTitle(String title) {
        return projectRepository.findProjectByTitle(title);
    }

    public Project save(Project project) {
        Optional<Project> optionalProject = findProjectByTitle(project.getTitle());

        optionalProject.ifPresent(
                s -> {
                    throw new NotUniqueProjectException(s.getTitle());
                }
        );

        return projectRepository.save(project);
    }
}

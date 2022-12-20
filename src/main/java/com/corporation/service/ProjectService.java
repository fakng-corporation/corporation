package com.corporation.service;

import com.corporation.exception.ProjectNotFoundException;
import com.corporation.model.Project;
import com.corporation.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project findById(long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        return optionalProject
                .orElseThrow(() -> new ProjectNotFoundException(
                        String.format("Project with id %d does not exist.", id)
                ));
    }
}

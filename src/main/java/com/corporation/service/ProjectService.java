package com.corporation.service;

import com.corporation.dto.ProjectDto;
import com.corporation.exception.ProjectNotFoundException;
import com.corporation.mapper.ProjectMapper;
import com.corporation.model.Project;
import com.corporation.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final ProjectMapper projectMapper;

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project update(Long id, ProjectDto projectDto) {
        if (projectRepository.existsById(id)) {
            projectDto.setId(id);
            Project project = projectMapper.toEntity(projectDto);
            return projectRepository.save(project);
        } else {
            throw new ProjectNotFoundException(
                    String.format("Project with id %d does not exist.", id));
        }
    }
}

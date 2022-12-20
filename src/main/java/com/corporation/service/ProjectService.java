package com.corporation.service;

import com.corporation.dto.ProjectDto;
import com.corporation.exception.ProjectNotFoundException;
import com.corporation.mapper.ProjectMapper;
import com.corporation.model.Project;
import com.corporation.model.User;
import com.corporation.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final ProjectMapper projectMapper;

    public ProjectDto add(ProjectDto projectDto) {
        Project project = projectMapper.toEntity(projectDto);
        setUserToProjectByIdFromDto(Optional.ofNullable(project), projectDto.getOwnerId());
        return saveEntityAndReturnDto(project);
    }

    public ProjectDto update(ProjectDto projectDto) {
        Optional<Project> project = projectRepository.findById(projectDto.getId());
        project.ifPresentOrElse(p -> projectMapper.updateFromDto(projectDto, project.get()),
                () -> {throw new ProjectNotFoundException(
                        String.format("Project with id %d does not exist.", projectDto.getId()));});
        setUserToProjectByIdFromDto(project, projectDto.getOwnerId());
        return saveEntityAndReturnDto(project.get());
    }

    private ProjectDto saveEntityAndReturnDto(Project project) {
        project = projectRepository.save(project);
        return projectMapper.toDto(project);
    }

    private void setUserToProjectByIdFromDto(Optional<Project> project, long ownerId) {
        if(project.get().getOwner().getId() != ownerId) {
            User user = userService.findById(ownerId);
            project.get().setOwner(user);
        }
    }
}

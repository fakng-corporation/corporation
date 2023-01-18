package com.corporation.service;

import com.corporation.exception.ProjectNotFoundException;
import com.corporation.dto.ProjectDto;
import com.corporation.mapper.ProjectMapper;
import com.corporation.model.Project;
import com.corporation.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        project.setOwner(userService.findById(projectDto.getOwnerId()));
        return saveEntityAndReturnDto(project);
    }

    public ProjectDto update(ProjectDto projectDto) {
        return projectRepository.findById(projectDto.getId())
                .map(project -> {
                    projectMapper.updateFromDto(projectDto, project);
                    return saveEntityAndReturnDto(project);
                })
                .orElseThrow(() -> new ProjectNotFoundException(
                        String.format("Project with id %d does not exist.", projectDto.getId())));
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    private ProjectDto saveEntityAndReturnDto(Project project) {
        project = projectRepository.save(project);
        return projectMapper.toDto(project);
    }

    public Project findById(long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        return optionalProject
                .orElseThrow(() -> new ProjectNotFoundException(
                        String.format("Project with id %d does not exist.", id)
                ));
    }

    public Page<ProjectDto> getProjectsByTitle(String keyword, int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return projectRepository.findByTitleContainingIgnoreCase(keyword, page).map(projectMapper::toDto);
    }

    @Transactional
    public long getProjectFollowersAmountByProjectId(long projectId) {
        return projectRepository.getProjectFollowersAmount(projectId);
    }
}
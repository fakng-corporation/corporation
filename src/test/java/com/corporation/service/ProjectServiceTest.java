package com.corporation.service;

import com.corporation.dto.ProjectDto;
import com.corporation.exception.ProjectNotFoundException;
import com.corporation.mapper.ProjectMapper;
import com.corporation.model.Project;
import com.corporation.model.User;
import com.corporation.repository.ProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private UserService userService;

    @Spy
    @InjectMocks
    private ProjectMapper projectMapper = Mappers.getMapper(ProjectMapper.class);

    @InjectMocks
    private ProjectService projectService;

    @Test
    public void shouldReturnCreatedProjectWithOwner() {
        long projectId = 10;
        String title = "title";
        long ownerId = 5;
        User owner = User.builder().id(ownerId).build();
        Project project = Project.builder().id(projectId).title(title).owner(owner).build();
        ProjectDto projectDto = projectMapper.toDto(project);

        Mockito.when(userService.findById(ownerId))
                .thenReturn(owner);
        Mockito.when(projectRepository.save(project))
                .thenReturn(project);
        ProjectDto returnedProject = projectService.add(projectDto);

        Assertions.assertEquals(projectId, returnedProject.getId());
        Assertions.assertEquals(title, returnedProject.getTitle());
        Assertions.assertEquals(ownerId, returnedProject.getOwnerId());
    }

    @Test
    public void shouldReturnUpdatedProjectWithOwner() {
        long projectId = 10;
        String oldTitle = "old title";
        String newTitle = "new title";
        Project project = Project.builder().id(projectId).title(oldTitle).build();
        Project updatedProject = Project.builder().id(projectId).title(newTitle).build();
        ProjectDto projectDto = projectMapper.toDto(updatedProject);

        Mockito.when(projectRepository.findById(projectId))
                .thenReturn(Optional.of(project));
        Mockito.when(projectRepository.save(project))
                .thenReturn(updatedProject);
        ProjectDto returnedProject = projectService.update(projectDto);

        Assertions.assertEquals(projectId, returnedProject.getId());
        Assertions.assertEquals(newTitle, returnedProject.getTitle());
    }

    @Test
    public void shouldThrowProjectNotFoundException() {

        long id = 100;

        Project project = Project.builder().id(id).build();
        ProjectDto projectDto = projectMapper.toDto(project);

        Mockito.when(projectRepository.findById(Mockito.any(long.class)))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                ProjectNotFoundException.class,
                () -> projectService.update(projectDto)
        );
    }

}

package com.corporation.controller;

import com.corporation.dto.ProjectDto;
import com.corporation.mapper.ProjectMapperImpl;
import com.corporation.model.Project;
import com.corporation.service.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @Spy
    private ProjectMapperImpl projectMapper;

    @InjectMocks
    private ProjectController projectController;

    @Test
    public void shouldReturnCreatedProjectDto() {

        long id = 777;
        String title = "bigproject";

        Project project = Project.builder().title(title).build();
        ProjectDto projectDto = projectMapper.toDto(project);
        Project projectWithId = Project.builder().id(id).title(title).build();
        ProjectDto addedProjectDto = projectMapper.toDto(projectWithId);

        Mockito.when(projectService.add(projectDto)).thenReturn(addedProjectDto);

        ProjectDto createdProjectDto = projectController.addProject(projectDto);

        Assertions.assertEquals(id, createdProjectDto.getId());
        Assertions.assertEquals(title, createdProjectDto.getTitle());
    }

    @Test
    public void shouldReturnUpdatedProjectDto() {

        long id = 777;
        String newTitle = "bigproject1";

        Project projectWithNewTitle = Project.builder().id(id).title(newTitle).build();
        ProjectDto projectDto = projectMapper.toDto(projectWithNewTitle);

        Mockito.when(projectService.update(projectDto)).thenReturn(projectDto);

        ProjectDto resultProjectDto = projectController.updateProject(projectDto);

        Assertions.assertEquals(id, resultProjectDto.getId());
        Assertions.assertEquals(newTitle, resultProjectDto.getTitle());
    }

    @Test
    public void shouldDeleteProject() {
        Project project = Project.builder().id(any(Long.class)).build();
        projectController.deleteProject(project.getId());

        Mockito.verify(projectService).delete(project.getId());
    }
}

package com.corporation.controller;

import com.corporation.dto.ProjectDto;
import com.corporation.mapper.ProjectMapperImpl;
import com.corporation.mapper.UserMapper;
import com.corporation.model.Project;
import com.corporation.model.User;
import com.corporation.service.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @Spy
    private ProjectMapperImpl projectMapper;

    @InjectMocks
    private ProjectController projectController;
    @Spy
    private UserMapper userMapper;

    @Test
    public void shouldReturnCreatedProjectDto() {

        long id = 777;
        String title = "bigproject";
        long ownerId = 7;

        Project project = Project.builder().title(title).build();
        User owner = User.builder().id(ownerId).build();
        ProjectDto projectDto = projectMapper.toDto(project);
        Project projectWithId = Project.builder().owner(owner).id(id).title(title).build();
        ProjectDto addedProjectDto = projectMapper.toDto(projectWithId);

        Mockito.when(projectService.add(projectDto, ownerId)).thenReturn(addedProjectDto);

        ProjectDto createdProjectDto = projectController.addProject(projectDto, ownerId);

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

    @Test
    public void shouldReturnProjectByTitle() {
        long projectId = 10;
        String title = "title";
        Project mockProject = Project.builder().id(projectId).title(title).build();
        ProjectDto mockProjectDto = projectMapper.toDto(mockProject);
        List<ProjectDto> projectDtoList = Collections.singletonList(mockProjectDto);
        Page<ProjectDto> projectDtoPage = new PageImpl<>(projectDtoList);

        Mockito.when(projectService.getProjectsByTitle(title, 0, 5))
                .thenReturn(projectDtoPage);
        Page<ProjectDto> page = projectController.getProjects(title, 0, 5);

        Assertions.assertEquals(page.getTotalElements(), projectDtoPage.getTotalElements());
        Assertions.assertEquals(page.getContent(), projectDtoPage.getContent());
    }

    @Test
    public void shouldReturnProjectFollowersAmount() {
        long projectId = 4L;
        long projectFollowersAmount = 1L;
        Mockito.when(projectService.getProjectFollowersAmount(projectId)).thenReturn(projectFollowersAmount);

        long actualFollowersAmount = projectController.getProjectFollowersAmount(projectId);
        Assertions.assertEquals(projectFollowersAmount, actualFollowersAmount);
    }
}
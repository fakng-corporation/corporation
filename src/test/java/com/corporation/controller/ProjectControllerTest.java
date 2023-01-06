package com.corporation.controller;

import com.corporation.dto.ProjectDto;
import com.corporation.dto.UserDto;
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
    void shouldFollowProject() {
        long projectId = 4L;
        long projectFollowerId = 1l;
        User follower = User.builder().id(projectFollowerId).nickname("User1").build();
        UserDto expectedUserDto = UserDto.builder().id(projectFollowerId).nickname("User1").build();

        Mockito.when(projectService.followProject(projectId, projectFollowerId)).thenReturn(follower);
        Mockito.when(userMapper.toDto(follower)).thenReturn(expectedUserDto);
        UserDto actualUserDto = projectController.followProject(projectId, projectFollowerId);
        Assertions.assertEquals(expectedUserDto, actualUserDto);
    }
}
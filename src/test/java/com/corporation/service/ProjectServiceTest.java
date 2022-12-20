package com.corporation.service;

import com.corporation.dto.ProjectDto;
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
    public void shouldReturnCreatedProject() {

        long projectId = 777;
        long ownerId = 10;
        String title = "bigproject";

        User owner = User
                .builder()
                .id(ownerId)
                .nickname("boba")
                .email("boba@boba.com")
                .password("qwerty")
                .build();

        Mockito.when(userService.findById(ownerId)).thenReturn(owner);

        Project project = Project.builder().title(title).owner(owner).build();
        ProjectDto projectDto = projectMapper.toDto(project);
        Project projectWithId = Project.builder().id(projectId).title(title).owner(owner).build();

        Mockito.when(projectRepository.save(project)).thenReturn(projectWithId);

        ProjectDto createdProject = projectService.add(projectDto);

        Assertions.assertEquals(projectId, createdProject.getId());
        Assertions.assertEquals(title, createdProject.getTitle());
        Assertions.assertEquals(ownerId, createdProject.getOwnerId());
    }

}

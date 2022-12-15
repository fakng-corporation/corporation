package com.corporation.controller;

import com.corporation.dto.ProjectDto;
import com.corporation.exception.NotUniqueProjectException;
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
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @Spy
    private ProjectMapperImpl projectMapper;

    @InjectMocks
    private ProjectController projectController;

    @Test
    public void shouldReturnCreatedProjectDtoAndStatus200() {

        long id = 777;
        String title = "bigproject";

        Project project = Project.builder().title(title).build();
        ProjectDto projectDto = projectMapper.toDto(project);
        Project projectWithId = Project.builder().id(id).title(title).build();

        Mockito.when(projectService.save(project)).thenReturn(projectWithId);

        ResponseEntity<ProjectDto> responseEntity
                = projectController.addProject(projectDto);

        Mockito.verify(projectMapper).toEntity(projectDto);

        Assertions.assertEquals(200, responseEntity.getStatusCode().value());

        ProjectDto createdProjectDto = responseEntity.getBody();

        assert createdProjectDto != null;
        Assertions.assertEquals(id, createdProjectDto.getId());
        Assertions.assertEquals(title, createdProjectDto.getTitle());
    }

    @Test
    public void shouldReturnStatus400() {
        String title = "bigproject";

        Project project = Project.builder().title(title).build();
        ProjectDto projectDto = projectMapper.toDto(project);

        Mockito.when(projectService.save(project)).thenThrow(NotUniqueProjectException.class);

        ResponseEntity<ProjectDto> responseEntity = projectController.addProject(projectDto);

        Assertions.assertEquals(400, responseEntity.getStatusCode().value());
    }
}

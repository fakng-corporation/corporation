package com.corporation.service;

import com.corporation.model.Project;
import com.corporation.repository.ProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

//    @Test
//    public void shouldReturnCreatedProject() {
//
//        long id = 777;
//        String title = "bigproject";
//
//        Project project = Project.builder().title(title).build();
//        Project projectWithId = Project.builder().id(id).title(title).build();
//
//        Mockito.when(projectRepository.save(project)).thenReturn(projectWithId);
//
//        Project createdProject = projectService.add(project);
//
//        Assertions.assertEquals(id, createdProject.getId());
//        Assertions.assertEquals(title, createdProject.getTitle());
//    }
}

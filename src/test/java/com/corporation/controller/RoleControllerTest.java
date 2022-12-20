package com.corporation.controller;

import com.corporation.dto.RoleDto;
import com.corporation.mapper.RoleMapperImpl;
import com.corporation.model.Project;
import com.corporation.model.Role;
import com.corporation.service.ProjectService;
import com.corporation.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @Mock
    private ProjectService projectService;

    @Spy
    private RoleMapperImpl roleMapper;

    @InjectMocks
    private RoleController roleController;

    @Test
    void shouldReturnCreatedPostDto() {

        long id = 1;
        String title = "roleTitle";
        String description = "roleDescription";

        int projectId = 1;
        String projectTitle = "projectTitle";
        String projectDescription = "projectDescription";

        Project mockProject = Project
                .builder()
                .id(projectId)
                .title(projectTitle)
                .description(projectDescription)
                .build();

        Role role = Role
                .builder()
                .title(title)
                .description(description)
                .build();

        Role roleWithId = Role
                .builder()
                .id(id)
                .title(title)
                .description(description)
                .project(mockProject)
                .build();

        RoleDto roleDto = roleMapper.toDto(role);

        Mockito.when(roleService.saveRole(role)).thenReturn(roleWithId);

        RoleDto createdRoleDto = roleController.createRole(roleDto);

        Mockito.verify(roleMapper).toEntity(roleDto);

        Assertions.assertEquals(id, createdRoleDto.getId());
        Assertions.assertEquals(title, createdRoleDto.getTitle());
        Assertions.assertEquals(description, createdRoleDto.getDescription());
        Assertions.assertEquals(projectId, createdRoleDto.getProjectId());
    }
}

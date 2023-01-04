package com.corporation.service;

import com.corporation.dto.RoleDto;
import com.corporation.exception.NotFoundEntityException;
import com.corporation.mapper.RoleMapper;
import com.corporation.model.Project;
import com.corporation.model.Role;
import com.corporation.repository.RoleRepository;
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

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Mock
    private ProjectService projectService;
    @Spy
    @InjectMocks
    private RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);

    @Test
    public void shouldReturnCreatedRoleWithProject() {
        long roleId = 1;
        String title = "title";
        long projectId = 2;
        Project project = Project.builder().id(projectId).build();
        Role role = Role.builder().id(roleId).title(title).project(project).build();
        RoleDto roleDto = roleMapper.toDto(role);

        Mockito.when(projectService.findById(projectId))
                .thenReturn(project);
        Mockito.when(roleRepository.save(role))
                .thenReturn(role);
        RoleDto returnedRole = roleService.add(roleDto);

        Assertions.assertEquals(roleId, returnedRole.getId());
        Assertions.assertEquals(title, returnedRole.getTitle());
        Assertions.assertEquals(projectId, returnedRole.getProjectId());
    }

    @Test
    public void shouldReturnUpdatedRole() {
        long roleId = 1;
        String oldTitle = "old title";
        String newTitle = "new title";
        Role role = Role.builder().id(roleId).title(oldTitle).build();
        Role updatedRole = Role.builder().id(roleId).title(newTitle).build();
        RoleDto roleDto = roleMapper.toDto(updatedRole);

        Mockito.when(roleRepository.findById(roleId))
                .thenReturn(Optional.of(role));
        Mockito.when(roleRepository.save(role))
                .thenReturn(updatedRole);
        RoleDto returnedRole = roleService.update(roleDto);

        Assertions.assertEquals(roleId, returnedRole.getId());
        Assertions.assertEquals(newTitle, returnedRole.getTitle());
    }

    @Test
    public void shouldThrowNotFoundEntityException() {
        long id = 1;
        Role role = Role.builder().id(id).build();
        RoleDto roleDto = roleMapper.toDto(role);

        Mockito.when(roleRepository.findById(any(long.class)))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(
                NotFoundEntityException.class,
                () -> roleService.update(roleDto)
        );
    }

    @Test
    public void shouldDeleteRole() {
        Role role = Role.builder().id(any(Long.class)).build();
        roleService.delete(role.getId());

        Mockito.verify(roleRepository).deleteById(role.getId());
    }
}

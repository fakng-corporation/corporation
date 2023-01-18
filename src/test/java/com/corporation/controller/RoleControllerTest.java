package com.corporation.controller;

import com.corporation.dto.RoleDto;
import com.corporation.mapper.RoleMapperImpl;
import com.corporation.model.Role;
import com.corporation.service.RoleService;
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
public class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @Spy
    private RoleMapperImpl roleMapper;

    @InjectMocks
    private RoleController roleController;

    @Test
    public void shouldReturnCreatedRoleDto() {
        long id = any(Long.class);
        String title = "cool role";

        Role role = Role.builder().title(title).build();
        RoleDto roleDto = roleMapper.toDto(role);
        Role roleWithId = Role.builder().id(id).title(title).build();
        RoleDto roleDtoWithId = roleMapper.toDto(roleWithId);

        Mockito.when(roleService.add(roleDto)).thenReturn(roleDtoWithId);

        RoleDto createdRoleDto = roleController.addRole(roleDto);

        Assertions.assertEquals(id, createdRoleDto.getId());
        Assertions.assertEquals(title, createdRoleDto.getTitle());
    }

    @Test
    public void shouldDeleteRole() {
        Role role = Role.builder().id(any(Long.class)).build();
        roleController.deleteRole(role.getId());

        Mockito.verify(roleService).delete(role.getId());
    }

    @Test
    public void shouldReturnUpdatedRoleDto() {

        long id = 1;
        String newTitle = "cool role";

        Role roleWithNewTitle = Role.builder().id(id).title(newTitle).build();
        RoleDto roleDto = roleMapper.toDto(roleWithNewTitle);

        Mockito.when(roleService.update(roleDto)).thenReturn(roleDto);

        RoleDto resultRoleDto = roleController.updateRole(roleDto);

        Assertions.assertEquals(id, resultRoleDto.getId());
        Assertions.assertEquals(newTitle, resultRoleDto.getTitle());
    }

    @Test
    public void shouldReturnRoleByTitle() {
        long roleId = 10;
        String title = "title";
        Role mockRole = Role.builder().id(roleId).title(title).build();
        RoleDto mockRoleDto = roleMapper.toDto(mockRole);
        List<RoleDto> roleDtoList = Collections.singletonList(mockRoleDto);
        Page<RoleDto> roleDtoPage = new PageImpl<>(roleDtoList);

        Mockito.when(roleService.getRolesByTitle(title, 0, 5)).thenReturn(roleDtoPage);
        Page<RoleDto> page = roleController.getRoles(title, 0, 5);

        Assertions.assertEquals(page.getTotalElements(), roleDtoPage.getTotalElements());
        Assertions.assertEquals(page.getContent(), roleDtoPage.getContent());

    }
}

package com.corporation.service;

import com.corporation.model.Role;
import com.corporation.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    public void shouldReturnCreatedRole() {

        long id = 1;
        String title = "title";
        String description = "description";

        Role mockRole = Role
                .builder()
                .title(title)
                .description(description)
                .build();

        Role mockRoleWithId = Role
                .builder()
                .id(id)
                .title(title)
                .description(description)
                .build();

        Mockito.when(roleRepository.save(mockRole)).thenReturn(mockRoleWithId);

        Role createdRole = roleService.saveRole(mockRole);

        Assertions.assertEquals(id, createdRole.getId());
        Assertions.assertEquals(title, createdRole.getTitle());
        Assertions.assertEquals(description, createdRole.getDescription());

    }
}

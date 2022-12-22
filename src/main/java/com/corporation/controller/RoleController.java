package com.corporation.controller;

import com.corporation.controller.api.RoleApi;
import com.corporation.dto.RoleDto;
import com.corporation.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController implements RoleApi {

    private final RoleService roleService;

    public RoleDto addRole(RoleDto roleDto) {
        return roleService.add(roleDto);
    }

    @Override
    public void deleteRole(Long id) {
        roleService.delete(id);
    }
}

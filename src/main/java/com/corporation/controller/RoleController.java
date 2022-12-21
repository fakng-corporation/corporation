package com.corporation.controller;

import com.corporation.dto.RoleDto;
import com.corporation.model.Role;
import com.corporation.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @PutMapping
    public RoleDto createRole(@RequestBody RoleDto roleDto) {
        Role role = roleService.setUpRole(roleDto);
        role = roleService.saveRole(role);
        return roleService.formatToDto(role);
    }
}

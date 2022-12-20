package com.corporation.controller;

import com.corporation.dto.RoleDto;
import com.corporation.mapper.RoleMapper;
import com.corporation.model.Project;
import com.corporation.model.Role;
import com.corporation.service.ProjectService;
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

    private final RoleMapper roleMapper;

    private final ProjectService projectService;

    @PutMapping
    public RoleDto createRole(@RequestBody RoleDto roleDto) {
        Project project = projectService.findById(roleDto.getProjectId());
        Role role = roleMapper.toEntity(roleDto);
        role.setProject(project);
        role = roleService.saveRole(role);
        RoleDto roleDtoToReturn = roleMapper.toDto(role);
        roleDtoToReturn.setProjectId(role.getProject().getId());
        return roleDtoToReturn;
    }
}

package com.corporation.service;

import com.corporation.dto.RoleDto;
import com.corporation.mapper.RoleMapper;
import com.corporation.model.Project;
import com.corporation.model.Role;
import com.corporation.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    private final ProjectService projectService;

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public RoleDto formatToDto(Role role) {
        RoleDto roleDto = roleMapper.toDto(role);
        roleDto.setProjectId(role.getProject().getId());
        return roleDto;
    }

    public Role setUpRole(RoleDto roleDto) {
        Project project = projectService.findById(roleDto.getProjectId());
        Role role = roleMapper.toEntity(roleDto);
        role.setProject(project);
        return role;
    }
}

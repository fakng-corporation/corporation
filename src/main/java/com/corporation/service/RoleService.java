package com.corporation.service;

import com.corporation.dto.RoleDto;
import com.corporation.mapper.RoleMapper;
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
    public RoleDto add(RoleDto roleDto) {
        Role role = roleMapper.toEntity(roleDto);
        role.setProject(projectService.findById(roleDto.getProjectId()));
        return saveEntityAndReturnDto(role);
    }

    private RoleDto saveEntityAndReturnDto(Role role) {
        role = roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}

package com.corporation.service;

import com.corporation.dto.RoleDto;
import com.corporation.exception.NotFoundEntityException;
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

    public RoleDto update(RoleDto roleDto) {
        return roleRepository.findById(roleDto.getId())
                .map(role -> {
                    roleMapper.updateFromDto(roleDto, role);
                    return saveEntityAndReturnDto(role);
                })
                .orElseThrow(() -> new NotFoundEntityException(
                        String.format("Role with id %d does not exist.", roleDto.getId())
                ));
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    private RoleDto saveEntityAndReturnDto(Role role) {
        role = roleRepository.save(role);
        return roleMapper.toDto(role);
    }
}

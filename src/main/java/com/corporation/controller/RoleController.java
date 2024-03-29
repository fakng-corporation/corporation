package com.corporation.controller;

import com.corporation.controller.api.RoleApi;
import com.corporation.dto.RoleDto;
import com.corporation.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public RoleDto updateRole(RoleDto roleDto) {
        return roleService.update(roleDto);
    }

    @Override
    public void deleteRole(Long id) {
        roleService.delete(id);
    }

    @Override
    public Page<RoleDto> getRoles(String keyword, int pageNumber, int pageSize) {
        return roleService.getRolesByTitle(keyword, pageNumber, pageSize);
    }
}

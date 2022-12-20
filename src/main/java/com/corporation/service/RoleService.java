package com.corporation.service;

import com.corporation.model.Role;
import com.corporation.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    private Optional<Role> findRoleBtTitle(String title) {
	   return roleRepository.findRoleByTitle(title);
    }

    public Role saveRole(Role role) {
	   return roleRepository.save(role);
    }
}

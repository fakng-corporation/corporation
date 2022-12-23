package com.corporation.repository;

import com.corporation.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findRoleByTitle(String title);
}

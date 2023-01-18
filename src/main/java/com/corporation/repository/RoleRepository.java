package com.corporation.repository;

import com.corporation.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Page<Role> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

}

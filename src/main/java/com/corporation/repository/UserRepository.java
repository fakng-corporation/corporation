package com.corporation.repository;

import com.corporation.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Page<User> findAll(Pageable pageable);
    Optional<User> findByNickname(String nickname);
}

package com.corporation.service;

import com.corporation.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@Component
public interface UserServiceInterface {

    Optional<User> findById(int id);
}

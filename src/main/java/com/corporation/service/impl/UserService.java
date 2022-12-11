package com.corporation.service.impl;

import com.corporation.model.User;
import com.corporation.repository.UserRepository;
import com.corporation.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
}

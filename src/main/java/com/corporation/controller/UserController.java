package com.corporation.controller;

import com.corporation.model.User;
import com.corporation.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceInterface userServiceInterface;

    @Autowired
    public UserController(UserServiceInterface userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        Optional<User> optionalUser = userServiceInterface.findById(id);
        return optionalUser.isEmpty()
                ? ResponseEntity.status(404).body(null)
                : ResponseEntity.ok(optionalUser.get());
    }
}

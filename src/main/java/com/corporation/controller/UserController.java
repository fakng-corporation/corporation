package com.corporation.controller;

import com.corporation.dto.UserDto;
import com.corporation.mapper.UserMapper;
import com.corporation.model.User;
import com.corporation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService UserService;

    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") int id) {
        Optional<User> optionalUser = UserService.findById(id);

        return optionalUser
                .map(user -> ResponseEntity.ok(userMapper.toUserDto(user)))
                .orElse(ResponseEntity.status(404).build());
    }
}

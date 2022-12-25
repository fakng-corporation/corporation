package com.corporation.controller;

import com.corporation.dto.UserDto;
import com.corporation.mapper.UserMapper;
import com.corporation.model.User;
import com.corporation.service.S3Service;
import com.corporation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Bleschunov Dmitry
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private final UserMapper userMapper;

    private final S3Service s3Service;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") long id) {
        User user = userService.findById(id);
        return userMapper.toUserDto(user);
    }

    @PostMapping("/{id}")
    public UserDto updateUser(@PathVariable("id") long id, @RequestBody UserDto userDto) {
        return userMapper.toUserDto(
                userService.update(id, userDto)
        );
    }

    @PostMapping("/{id}/avatar")
    public void uploadUserAvatar(
            @PathVariable("id") long id,
            @RequestParam("userAvatar") MultipartFile userAvatar) {
        userService.updateUserAvatar(id, userAvatar);
    }
}

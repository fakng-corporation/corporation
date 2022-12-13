package com.corporation.controller;

import com.corporation.dto.UserDto;
import com.corporation.model.User;
import com.corporation.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@SpringBootTest
public class UserControllerTest {
    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;

    @Test
    public void shouldReturnUserDtoByIdAndStatus200() {
        int desiredId = 1;
        String nickname = "boba";
        String email = "boba@boba.com";
        String password = "1234";
        String aboutMe = "I am boba!";

        User mockUser = User
                .builder()
                .id(desiredId)
                .nickname(nickname)
                .email(email)
                .password(password)
                .aboutMe(aboutMe)
                .build();

        Mockito.when(userService.findById(desiredId))
                .thenReturn(Optional.of(mockUser));

        ResponseEntity<UserDto> userResponseEntity = userController.getUserById(desiredId);

        Assertions.assertEquals(200, userResponseEntity.getStatusCode().value());

        UserDto userDto = userResponseEntity.getBody();

        Assertions.assertEquals(nickname, userDto.getNickname());
        Assertions.assertEquals(aboutMe, userDto.getAboutMe());
        Assertions.assertEquals(email, userDto.getEmail());
        Assertions.assertEquals(password, userDto.getPassword());
        Assertions.assertEquals(desiredId, userDto.getId());
    }

    @Test
    public void shouldReturnNullAndStatus404() {

        int desiredId = 100;

        Mockito.when(userService.findById(desiredId))
                .thenReturn(Optional.empty());

        ResponseEntity<UserDto> userResponseEntity = userController.getUserById(desiredId);

        Assertions.assertEquals(404, userResponseEntity.getStatusCode().value());
    }
}

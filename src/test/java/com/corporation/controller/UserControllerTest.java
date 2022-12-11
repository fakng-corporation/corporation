package com.corporation.controller;

import com.corporation.model.User;
import com.corporation.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void shouldReturnUserByIdAndStatus200() {
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

        ResponseEntity<User> userResponseEntity = userController.getUserById(desiredId);

        Assertions.assertEquals(200, userResponseEntity.getStatusCode().value());

        User user = userResponseEntity.getBody();

        Assertions.assertEquals(desiredId, user.getId());
        Assertions.assertEquals(nickname, user.getNickname());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals(aboutMe, user.getAboutMe());
    }

    @Test
    public void shouldReturnNullAndStatus404() {

        int desiredId = 100;

        Mockito.when(userService.findById(desiredId))
                .thenReturn(Optional.empty());

        ResponseEntity<User> userResponseEntity = userController.getUserById(desiredId);

        Assertions.assertEquals(404, userResponseEntity.getStatusCode().value());
    }
}

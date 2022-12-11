package com.corporation;

import com.corporation.controller.UserController;
import com.corporation.model.User;
import com.corporation.service.impl.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userServiceMock;

    @InjectMocks
    private UserController userController;

    @Test
    public void getExistingUserById() {
        int desiredId = 1;
        String nickname = "boba";
        String email = "boba@boba.com";
        String password = "1234";
        String aboutMe = "I am boba!";

        Mockito.when(userServiceMock.findById(desiredId))
                .thenReturn(Optional.of(new User(desiredId, nickname, email, password, aboutMe)));

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
    public void getNotExistingUserById() {

        int desiredId = 100;

        Mockito.when(userServiceMock.findById(desiredId))
                .thenReturn(Optional.ofNullable(null));

        ResponseEntity<User> userResponseEntity = userController.getUserById(desiredId);

        Assertions.assertEquals(404, userResponseEntity.getStatusCode().value());
    }
}

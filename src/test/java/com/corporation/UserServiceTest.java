package com.corporation;

import com.corporation.model.User;
import com.corporation.repository.UserRepository;
import com.corporation.service.impl.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserService userService;

    @Test
    public void getExistingUserById() {

        int desiredId = 1;
        String nickname = "boba";
        String email = "boba@boba.com";
        String password = "1234";
        String aboutMe = "I am boba!";

        Mockito.when(userRepositoryMock.findById(desiredId))
                        .thenReturn(Optional.of(new User(desiredId, nickname, email, password, aboutMe)));

        Optional<User> optionalUser = userService.findById(desiredId);

        Assertions.assertTrue(optionalUser.isPresent());

        User user = optionalUser.get();

        Assertions.assertEquals(desiredId, user.getId());
        Assertions.assertEquals(nickname, user.getNickname());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals(aboutMe, user.getAboutMe());
    }

    @Test
    public void getNotExistingUserById() {

        int desiredId = 100;

        Mockito.when(userRepositoryMock.findById(desiredId))
                .thenReturn(Optional.ofNullable(null));

        Optional<User> optionalUser = userService.findById(desiredId);

        Assertions.assertFalse(optionalUser.isPresent());
    }
}

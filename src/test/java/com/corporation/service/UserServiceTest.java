package com.corporation.service;

import com.corporation.dto.UserDto;
import com.corporation.exception.UserNotFoundException;
import com.corporation.model.User;
import com.corporation.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldReturnUserById() {
        long desiredId = 1;
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

        Mockito.when(userRepository.findById(desiredId))
                .thenReturn(Optional.of(mockUser));

        User user = userService.findById(desiredId);

        Assertions.assertEquals(desiredId, user.getId());
        Assertions.assertEquals(nickname, user.getNickname());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals(aboutMe, user.getAboutMe());
    }

    @Test
    public void shouldReturnUpdatedUser() {
        long desiredId = 1;
        String oldNickname = "boba";
        String oldEmail = "boba@boba.com";
        String oldAboutMe = "I am boba!";
        String newNickname = "new boba";
        String newEmail = "new_boba@boba.com";
        String newAboutMe = "I am new boba!";

        User oldUser = User
                .builder()
                .id(desiredId)
                .nickname(oldNickname)
                .email(oldEmail)
                .aboutMe(oldAboutMe)
                .build();

        UserDto newUserDto = UserDto
                .builder()
                .id(desiredId)
                .nickname(newNickname)
                .email(newEmail)
                .aboutMe(newAboutMe)
                .build();

        Mockito.when(userRepository.findById(desiredId))
                .thenReturn(Optional.of(oldUser));
        User user = userService.update(newUserDto);

        Assertions.assertEquals(desiredId, user.getId());
        Assertions.assertEquals(newNickname, user.getNickname());
        Assertions.assertEquals(newEmail, user.getEmail());
        Assertions.assertEquals(newAboutMe, user.getAboutMe());
    }

    @Test
    public void shouldThrowUserNotFoundException() {
        long desiredId = 100;

        Mockito.when(userRepository.findById(desiredId))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.findById(desiredId));
    }
}

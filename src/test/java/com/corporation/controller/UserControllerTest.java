package com.corporation.controller;

import com.corporation.dto.UserDto;
import com.corporation.exception.UserNotFoundException;
import com.corporation.mapper.UserMapperImpl;
import com.corporation.model.User;
import com.corporation.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @Spy
    private UserMapperImpl userMapper;

    @InjectMocks
    private UserController userController;

    @Test
    public void shouldReturnUserDtoById() {
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
                .thenReturn(mockUser);

        UserDto userDto = userController.getUserById(desiredId);

        Mockito.verify(userMapper).toDto(mockUser);

        Assertions.assertEquals(nickname, userDto.getNickname());
        Assertions.assertEquals(aboutMe, userDto.getAboutMe());
        Assertions.assertEquals(email, userDto.getEmail());
        Assertions.assertEquals(desiredId, userDto.getId());
    }

    @Test
    public void shouldReturnUpdatedUserDto() {
        long desiredId = 1;
        String newNickname = "new boba";
        String newEmail = "new_boba@boba.com";
        String newAboutMe = "I am new boba!";
        User newUser = User
                .builder()
                .id(desiredId)
                .nickname(newNickname)
                .email(newEmail)
                .aboutMe(newAboutMe)
                .build();
        UserDto newUserDto = userMapper.toUserDto(newUser);

        Mockito.when(userService.update(newUserDto))
                .thenReturn(newUser);
        UserDto returnedUserDto = userController.updateUser(newUserDto);

        Assertions.assertEquals(newNickname, returnedUserDto.getNickname());
        Assertions.assertEquals(newAboutMe, returnedUserDto.getAboutMe());
        Assertions.assertEquals(newEmail, returnedUserDto.getEmail());
        Assertions.assertEquals(desiredId, returnedUserDto.getId());
    }

    @Test
    public void shouldThrowUserNotFoundException() {

        int desiredId = 100;

        Mockito.when(userService.findById(desiredId))
                .thenThrow(UserNotFoundException.class);

        Assertions.assertThrows(UserNotFoundException.class, () -> userController.getUserById(desiredId));
    }
}

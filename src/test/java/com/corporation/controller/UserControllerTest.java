package com.corporation.controller;

import com.corporation.dto.UserDto;
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
import org.springframework.http.ResponseEntity;

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
                .thenReturn(mockUser);

        ResponseEntity<UserDto> userResponseEntity = userController.getUserById(desiredId);

        Mockito.verify(userMapper).toUserDto(mockUser);

        Assertions.assertEquals(200, userResponseEntity.getStatusCode().value());

        UserDto userDto = userResponseEntity.getBody();

        Assertions.assertEquals(nickname, userDto.getNickname());
        Assertions.assertEquals(aboutMe, userDto.getAboutMe());
        Assertions.assertEquals(email, userDto.getEmail());
        Assertions.assertEquals(password, userDto.getPassword());
        Assertions.assertEquals(desiredId, userDto.getId());
    }
}

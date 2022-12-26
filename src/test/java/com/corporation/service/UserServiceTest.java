package com.corporation.service;

import com.corporation.dto.AuthDto;
import com.corporation.dto.UserDto;
import com.corporation.exception.EntityNotFoundException;
import com.corporation.exception.EntityNotUniqueException;
import com.corporation.mapper.UserMapper;
import com.corporation.model.User;
import com.corporation.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

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
    public void shouldReturnUserDetails() {
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

        Mockito.when(userRepository.findByNickname(nickname))
                .thenReturn(Optional.of(mockUser));

        User user = (User) userService.loadUserByUsername(nickname);

        Assertions.assertEquals(desiredId, user.getId());
        Assertions.assertEquals(nickname, user.getNickname());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals(aboutMe, user.getAboutMe());
    }

    @Test
    public void shouldSaveUser() {
        AuthDto authDto = AuthDto.builder()
                .username("boba")
                .password("qwerty")
                .email("boba@boba.ru")
                .build();

        Mockito.when(userRepository.findByNickname(authDto.getUsername()))
                .thenReturn(Optional.empty());
        Mockito.when(userRepository.findByEmail(authDto.getEmail()))
                .thenReturn(Optional.empty());
        userService.register(authDto);

        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }

    @Test
    public void shouldThrowEntityNotUniqueByNickname() {
        AuthDto authDto = AuthDto.builder()
                .username("boba")
                .password("qwerty")
                .email("boba@boba.ru")
                .build();
        User user = User.builder().build();

        Mockito.when(userRepository.findByNickname(authDto.getUsername()))
                .thenReturn(Optional.of(user));
        Assertions.assertThrows(EntityNotUniqueException.class, () -> userService.register(authDto));
    }

    @Test
    public void shouldThrowEntityNotUniqueByEmail() {
        AuthDto authDto = AuthDto.builder()
                .username("boba")
                .password("qwerty")
                .email("boba@boba.ru")
                .build();
        User user = User.builder().build();

        Mockito.when(userRepository.findByNickname(authDto.getUsername()))
                .thenReturn(Optional.empty());
        Mockito.when(userRepository.findByNickname(authDto.getUsername()))
                .thenReturn(Optional.of(user));
        Assertions.assertThrows(EntityNotUniqueException.class, () -> userService.register(authDto));
    }

    @Test
    public void shouldThrowUserNotFoundException() {
        long desiredId = 100;

        Mockito.when(userRepository.findById(desiredId))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> userService.findById(desiredId));
    }
}

package com.corporation.service.auth;

import com.corporation.dto.AuthDto;
import com.corporation.exception.BusinessException;
import com.corporation.exception.NotUniqueEntityException;
import com.corporation.model.User;
import com.corporation.repository.UserRepository;
import com.corporation.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @Spy
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    @Test
    public void shouldSaveUser() {
        AuthDto authDto = AuthDto.builder()
                .username("boba")
                .password("qwerty")
                .confirmedPassword("qwerty")
                .email("boba@boba.ru")
                .build();

        authService.register(authDto);

        Mockito.verify(passwordEncoder).encode(authDto.getPassword());
        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }

    @Test
    public void shouldThrowNotUniqueEntityException() {
        AuthDto authDto = AuthDto.builder()
                .username("boba")
                .password("qwerty")
                .confirmedPassword("qwerty")
                .email("boba@boba.ru")
                .build();
        User user = User.builder().build();

        Mockito.when(userService.findByNicknameOrEmail(authDto.getUsername(), authDto.getEmail()))
                .thenReturn(Optional.of(user));
        Assertions.assertThrows(NotUniqueEntityException.class, () -> authService.register(authDto));
    }

    @Test
    public void shouldThrowBusinessExceptionOnUnmatchedPasswords() {
        AuthDto authDto = AuthDto.builder()
                .username("boba")
                .password("qwerty")
                .confirmedPassword("1234")
                .email("boba@boba.ru")
                .build();

        Assertions.assertThrows(BusinessException.class, () -> authService.register(authDto));
    }
}

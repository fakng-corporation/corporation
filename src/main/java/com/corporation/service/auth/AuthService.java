package com.corporation.service.auth;

import com.corporation.Util.UserRole;
import com.corporation.configuration.jwt.TokenProvider;
import com.corporation.dto.AuthDto;
import com.corporation.exception.BusinessException;
import com.corporation.exception.NotUniqueEntityException;
import com.corporation.model.Authority;
import com.corporation.model.User;
import com.corporation.repository.UserRepository;
import com.corporation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Bleschunov Dmitry
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserRepository userRepository;

    public void register(AuthDto authDto) {
        assertPasswordsMatch(authDto);
        assertNicknameAndEmailUnique(authDto);
        userRepository.save(createNewUserToSave(authDto));
    }

    public String authenticate(AuthDto authDto) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(
                authDto.getUsername(),
                authDto.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createToken(authentication);
    }

    private void assertNicknameAndEmailUnique(AuthDto authDto) {
        userService.findByNicknameOrEmail(authDto.getUsername(), authDto.getEmail())
                .ifPresent(user -> {
                    throw new NotUniqueEntityException(
                            String.format(
                                    "User with username %s or email %s already exists.",
                                    user.getUsername(),
                                    user.getEmail()
                            )
                    );
                });
    }

    private void assertPasswordsMatch(AuthDto authDto) {
        if (!authDto.getPassword().equals(authDto.getConfirmedPassword())) {
            throw new BusinessException("Passwords don't match.");
        }
    }

    private User createNewUserToSave(AuthDto authDto) {
        String hashedPassword = passwordEncoder.encode(authDto.getPassword());

        return User
                .builder()
                .nickname(authDto.getUsername())
                .email(authDto.getEmail())
                .password(hashedPassword)
                .enabled(true)
                .authority(Authority.builder().id(UserRole.ROLE_USER.getId()).build())
                .build();
    }
}

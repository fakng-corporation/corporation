package com.corporation.controller;

import com.corporation.configuration.jwt.TokenProvider;
import com.corporation.dto.AuthDto;
import com.corporation.dto.UserDto;
import com.corporation.exception.UserNotFoundException;
import com.corporation.mapper.UserMapperImpl;
import com.corporation.model.User;
import com.corporation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bleschunov Dmitry
 */
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final UserMapperImpl userMapper;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody AuthDto authDto) {

        User user = userRepository.findByNickname(authDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with nickname %s does not exist.", authDto.getUsername())
                ));

        UserDto userDto = userMapper.toDto(user);

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(
                        authDto.getUsername(),
                        authDto.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwt);

        return new ResponseEntity<>(userDto, headers, HttpStatus.OK);
    }
}

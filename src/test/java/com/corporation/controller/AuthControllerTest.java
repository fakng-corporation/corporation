package com.corporation.controller;

import com.corporation.mapper.UserMapperImpl;
import com.corporation.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Spy
    private UserMapperImpl userMapper;

    @Mock
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Mock
    private SecurityContextHolder securityContextHolder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthController authController;

    @Test
    public void shouldReturnUserDtoAndBearerToken() {
//        String username = "username";
//        String password = "password";
//        AuthDto authDto = AuthDto.builder().username(username).password(password).build();

//        User user = User.builder().nickname(username).password(password).build();
//        UserDto userDto = userMapper.toDto(user);
//
//        UsernamePasswordAuthenticationToken authenticationToken
//                = new UsernamePasswordAuthenticationToken(
//                authDto.getUsername(),
//                authDto.getPassword()
//        );
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
//
//        Mockito.when(authenticationManagerBuilder.getObject())
//                        .thenReturn(new ProviderManager());
//
//        Mockito.when(authenticationManagerBuilder.getObject().authenticate(authenticationToken))
//                        .thenReturn(authentication);


//        Mockito.when(Mockito.spy(SecurityContextHolder.class));

//        Mockito.when(userRepository.findByNickname(username))
//                .thenReturn(Optional.of(user));
//
//
//        ResponseEntity<UserDto> responseEntity = authController.login(authDto);
//
//        Assertions.assertEquals(userDto.getNickname(), responseEntity.getBody().getNickname());
//        Assertions.assertEquals(userDto.getPassword(), responseEntity.getBody().getPassword());
//        Assertions.assertEquals(1, responseEntity.getHeaders().containsKey("Authorization"));
//        Assertions.assertEquals(1, responseEntity.getHeaders().get("Authorization").size());
//        Assertions.assertTrue(responseEntity.getHeaders().get("Authorization").get(0).startsWith("Bearer"));
//        Assertions.assertFalse(responseEntity.getHeaders().get("Authorization").get(0).equals("Bearer"));


    }
}

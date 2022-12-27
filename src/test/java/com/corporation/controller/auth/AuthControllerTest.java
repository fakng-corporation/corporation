package com.corporation.controller.auth;

import com.corporation.dto.AuthDto;
import com.corporation.service.auth.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void shouldRegisterUser() {
        AuthDto authDto = AuthDto.builder().build();

        authController.register(authDto);

        Mockito.verify(authService).register(authDto);
    }

    @Test
    public void shouldAuthenticateUserAndReturnJwtInHeaders() {
        String jwt = "<jwtToken>";
        AuthDto authDto = AuthDto.builder().build();

        Mockito.when(authService.authenticate(authDto))
                .thenReturn(jwt);
        ResponseEntity<String> responseEntity = authController.login(authDto);

        String authorizationHeader = responseEntity.getHeaders().get("Authorization").get(0);

        Assertions.assertTrue(authorizationHeader.startsWith("Bearer "));
        Assertions.assertNotEquals("Bearer ", authorizationHeader);
        Assertions.assertEquals(jwt, authorizationHeader.substring(authorizationHeader.indexOf(' ') + 1));

        Mockito.verify(authService).authenticate(authDto);
    }
}

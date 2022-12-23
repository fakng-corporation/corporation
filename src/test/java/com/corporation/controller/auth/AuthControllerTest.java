package com.corporation.controller.auth;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

//    @Mock
//    private AuthenticationManagerBuilder authenticationManagerBuilder;
//
//    @InjectMocks
//    private AuthController authController;
//
//    @Test
//    public void shouldReturnUserDtoAndBearerToken() {
//        String username = "username";
//        String password = "password";
//        AuthDto authDto = AuthDto.builder().username(username).password(password).build();
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
//
//        Mockito.mock(SecurityContextHolder.class);
//
//        ResponseEntity<String> responseEntity = authController.login(authDto);
//
//        Assertions.assertTrue(responseEntity.getHeaders().containsKey("Authorization"));
//        Assertions.assertEquals(1, responseEntity.getHeaders().get("Authorization").size());
//        Assertions.assertTrue(responseEntity.getHeaders().get("Authorization").get(0).startsWith("Bearer"));
//        Assertions.assertNotEquals("Bearer", responseEntity.getHeaders().get("Authorization").get(0));
//    }
}

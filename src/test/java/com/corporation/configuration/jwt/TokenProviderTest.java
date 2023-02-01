package com.corporation.configuration.jwt;

import com.corporation.model.User;
import com.corporation.service.UserService;
import com.corporation.util.UserRole;
import io.jsonwebtoken.JwtParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class TokenProviderTest {

    private UserService userService;
    private TokenProvider tokenProvider;

    @BeforeEach
    public void setUp() {
        this.userService = Mockito.mock(UserService.class);
        tokenProvider = new TokenProvider("ZG1pdHJ5Ymxlc2NodW5vdl9pc19zdXBlcl9wdXBlcl9jbGFzcwo", userService);
    }

    @Test
    public void shouldReturnJwtToken() {
        String principal = "username";
        String credentials = "password";

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        principal,
                        credentials
                );

        String token = tokenProvider.createToken(authentication);
        JwtParser parser = tokenProvider.getParser();

        Assertions.assertNotNull(parser.parseClaimsJws(token));
    }

    @Test
    public void shouldReturnAuthentication() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2JhIiwiYXV0aCI6IlJPTEVfVVNFUiJ9.r4YIpClbz47ZgPsTBApKGjnyXqW7cZfpFw_8t13heKI";

        User user = User.builder().id(1).nickname("boba").build();
        Mockito.when(userService.loadUserByUsername("boba")).thenReturn(user);

        Authentication authentication = tokenProvider.getAuthentication(token);

        User principal = (User) authentication.getPrincipal();
        List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) authentication.getAuthorities();

        Assertions.assertEquals("boba", principal.getUsername());
        Assertions.assertEquals(1, principal.getId());
        Assertions.assertEquals(1, authorities.size());
        Assertions.assertEquals(UserRole.ROLE_USER.value, authorities.get(0).getAuthority());

    }

    @Test
    public void shouldSuccessfullyValidateToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2JhIiwiYXV0aCI6IlJPTEVfVVNFUiJ9.r4YIpClbz47ZgPsTBApKGjnyXqW7cZfpFw_8t13heKI";

        Assertions.assertTrue(tokenProvider.validateToken(token));
    }


}

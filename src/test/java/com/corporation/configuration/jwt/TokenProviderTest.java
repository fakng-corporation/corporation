package com.corporation.configuration.jwt;

import com.corporation.Util.UserRole;
import io.jsonwebtoken.JwtParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @author Bleschunov Dmitry
 */
@ExtendWith(MockitoExtension.class)
public class TokenProviderTest {

    private final TokenProvider tokenProvider =
            new TokenProvider("ZG1pdHJ5Ymxlc2NodW5vdl9pc19zdXBlcl9wdXBlcl9jbGFzcwo");

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
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2JhIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY3MTcyNzA3OX0.8IVTattW_6uSelcdwCs8W0RHZ78MDOuzw7Wfe-9o9iw";

        Authentication authentication = tokenProvider.getAuthentication(token);

        User principal = (User) authentication.getPrincipal();
        List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) authentication.getAuthorities();

        Assertions.assertEquals("boba", principal.getUsername());
        Assertions.assertEquals("", principal.getPassword());
        Assertions.assertEquals(1, authorities.size());
        Assertions.assertEquals(UserRole.ROLE_USER.value, authorities.get(0).getAuthority());

    }

    @Test
    public void shouldReturnTrue() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2JhIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY3MTcyNzA3OX0.8IVTattW_6uSelcdwCs8W0RHZ78MDOuzw7Wfe-9o9iw";

        Assertions.assertTrue(tokenProvider.validateToken(token));
    }


}
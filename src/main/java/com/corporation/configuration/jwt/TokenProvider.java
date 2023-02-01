package com.corporation.configuration.jwt;

import com.corporation.model.User;
import com.corporation.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author Bleschunov Dmitry
 */
@Component
public class TokenProvider {
    private final Key key;
    private final JwtParser parser;

    private final UserService userService;

    public TokenProvider(@Value("${spring.security.secret}") String base64Secret, UserService userService) {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        key = Keys.hmacShaKeyFor(keyBytes);
        parser = Jwts.parserBuilder().setSigningKey(key).build();
        this.userService = userService;
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        Date validity = new Date(new Date().getTime() + 3600 * 1000);

        return Jwts
                .builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parser.parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get("auth").toString().split(", "))
                .map(SimpleGrantedAuthority::new)
                .toList();

        User principalUser = userService.loadUserByUsername(claims.getSubject());

        return new UsernamePasswordAuthenticationToken(principalUser, token, authorities);
    }

    public boolean validateToken(String token) {
        parser.parseClaimsJws(token);
        return true;
    }

    public JwtParser getParser() {
        return parser;
    }
}

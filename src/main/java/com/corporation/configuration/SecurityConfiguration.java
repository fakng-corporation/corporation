package com.corporation.configuration;

import com.corporation.util.UserRole;
import com.corporation.configuration.jwt.JwtConfigurer;
import com.corporation.configuration.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

/**
 * @author Bleschunov Dmitry
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final TokenProvider tokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .headers()
            .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
            .and()
            .frameOptions()
            .deny()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/login", "/register")
            .permitAll()
            .requestMatchers("/**")
            .hasAnyAuthority(UserRole.ROLE_USER.value)
            .anyRequest()
            .authenticated()
            .and()
            .apply(jwtSecurityConfigurerAdapter());

        return http.build();
    }

    private JwtConfigurer jwtSecurityConfigurerAdapter() {
        return new JwtConfigurer(tokenProvider);
    }
}

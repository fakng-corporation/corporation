package com.corporation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Bleschunov Dmitry
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {
    private String username;
    private String email;
    private String password;
    private String confirmedPassword;
}

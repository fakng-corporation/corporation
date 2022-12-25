package com.corporation.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Bleschunov Dmitry
 */
@Data
@Builder
public class AuthDto {
    private String username;
    private String password;
}

package com.corporation.dto;

import com.corporation.model.Authority;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Bleschunov Dmitry
 */
@Data
public class UserDto {
    private int id;
    private String nickname;
    private String email;
    private String password;
    private boolean enabled;
    private String aboutMe;
    private Authority authority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

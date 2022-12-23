package com.corporation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Bleschunov Dmitry
 */
@Data
@Builder
public class UserDto {
    private int id;
    private String nickname;
    private String email;
    private String password;
    private String aboutMe;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

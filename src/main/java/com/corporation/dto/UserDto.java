package com.corporation.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Bleschunov Dmitry
 */
@Data
@Builder
public class UserDto {
    private long id;
    private String nickname;
    private String email;
    private String aboutMe;
    private String avatarUrl;
}

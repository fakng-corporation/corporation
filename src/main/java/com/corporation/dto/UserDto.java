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
public class UserDto {
    private long id;
    private String nickname;
    private String email;
    private String aboutMe;
}

package com.corporation.exception;

import lombok.Getter;

/**
 * @author Bleschunov Dmitry
 */
@Getter
public class NotUniqueSkillException extends BusinessException {
    public NotUniqueSkillException(Object... params) {
        super(params);
    }
}

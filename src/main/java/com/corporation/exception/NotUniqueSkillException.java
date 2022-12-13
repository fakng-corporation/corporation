package com.corporation.exception;

/**
 * @author Bleschunov Dmitry
 */
public class NotUniqueSkillException extends RuntimeException {
    public NotUniqueSkillException(String skillTitle) {
        super(String.format("A skill with the title %s already exists.", skillTitle));
    }
}

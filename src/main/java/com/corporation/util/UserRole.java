package com.corporation.util;

import lombok.RequiredArgsConstructor;

/**
 * @author Bleschunov Dmitry
 */
@RequiredArgsConstructor
public enum UserRole {
    ROLE_USER("ROLE_USER");

    public final String value;

    public short getId() {
        return (short) (ordinal() + 1);
    }
}

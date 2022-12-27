package com.corporation.Util;

import lombok.RequiredArgsConstructor;

/**
 * @author Bleschunov Dmitry
 */
@RequiredArgsConstructor
public enum UserRole {
    ROLE_USER((short) 1, "ROLE_USER");

    public final short id;
    public final String value;
}

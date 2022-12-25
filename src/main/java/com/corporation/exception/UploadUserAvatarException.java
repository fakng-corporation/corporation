package com.corporation.exception;

/**
 * @author Bleschunov Dmitry
 */
public class UploadUserAvatarException extends BusinessException {
    public UploadUserAvatarException(String message) {
        super(message);
    }

    public UploadUserAvatarException(String message, Throwable cause) {
        super(message, cause);
    }
}

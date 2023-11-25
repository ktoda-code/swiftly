package com.ktoda.swiftly.backend.user.exceptions;

import com.ktoda.swiftly.backend.common.exceptions.CustomException;

public class UserAlreadyExistsException extends CustomException {
    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

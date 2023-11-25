package com.ktoda.swiftly.backend.user.exceptions;

import com.ktoda.swiftly.backend.common.exceptions.CustomException;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

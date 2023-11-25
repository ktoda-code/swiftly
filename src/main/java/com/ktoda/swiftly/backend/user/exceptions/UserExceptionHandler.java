package com.ktoda.swiftly.backend.user.exceptions;

import com.ktoda.swiftly.backend.common.exceptions.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Exception> handleExceptionNotFound(UserNotFoundException e) {
        Exception exception = new Exception(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Exception> handleExceptionAlreadyExists(UserAlreadyExistsException e) {
        Exception exception = new Exception(
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception);
    }

}

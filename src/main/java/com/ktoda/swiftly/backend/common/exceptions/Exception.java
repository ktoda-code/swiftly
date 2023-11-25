package com.ktoda.swiftly.backend.common.exceptions;

import java.time.ZonedDateTime;

public record Exception(String message, int status, ZonedDateTime timestamp) {
}

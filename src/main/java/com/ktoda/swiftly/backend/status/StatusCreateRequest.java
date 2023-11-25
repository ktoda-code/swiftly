package com.ktoda.swiftly.backend.status;

import jakarta.validation.constraints.NotNull;

import java.awt.*;

public record StatusCreateRequest(
        @NotNull
        String name,
        Color color // Default color is (null)
) {
}

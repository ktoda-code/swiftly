package com.ktoda.swiftly.backend.board;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BoardCreateRequest(
        @Size(max = 50)
        @NotNull
        String name
) {
}

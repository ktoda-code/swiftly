package com.ktoda.swiftly.backend.task;

import com.ktoda.swiftly.backend.property.Property;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record TaskCreateRequest(
        @NotNull
        String title,
        String description,
        Priority priority,
        List<Property> properties
) {
}

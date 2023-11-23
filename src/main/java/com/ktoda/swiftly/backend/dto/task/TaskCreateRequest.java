package com.ktoda.swiftly.backend.dto.task;

import com.ktoda.swiftly.backend.model.property.Property;
import com.ktoda.swiftly.backend.model.task.Priority;
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

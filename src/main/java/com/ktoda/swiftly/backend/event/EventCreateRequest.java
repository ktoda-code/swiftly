package com.ktoda.swiftly.backend.event;

import com.ktoda.swiftly.backend.task.Task;
import com.ktoda.swiftly.backend.taskgroup.TaskGroup;
import jakarta.validation.constraints.NotNull;

import java.awt.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.TimeZone;

public record EventCreateRequest(
        @NotNull
        String title,
        String description,
        Boolean allDay,
        @NotNull
        Date date,
        @NotNull
        Time startTime,
        @NotNull
        Time endTime,
        TimeZone timeZone,
        @NotNull
        EventType eventType,
        @NotNull
        Color color,
        List<Task> tasks,
        List<TaskGroup> taskGroups
) {
}

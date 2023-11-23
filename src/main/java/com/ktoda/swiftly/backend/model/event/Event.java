package com.ktoda.swiftly.backend.model.event;

import com.ktoda.swiftly.backend.model.user.User;
import com.ktoda.swiftly.backend.model.task.Task;
import com.ktoda.swiftly.backend.model.task.TaskGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TimeZone;
import java.util.List;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Boolean allDay;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Temporal(TemporalType.TIME)
    private Time startTime;
    @Temporal(TemporalType.TIME)
    private Time endTime;
    private TimeZone timeZone;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private Color color;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "event")
    @EqualsAndHashCode.Exclude
    private List<Task> tasks = new ArrayList<>();
    @OneToMany(mappedBy = "event")
    @EqualsAndHashCode.Exclude
    private List<TaskGroup> taskGroups = new ArrayList<>();

    public Event(String title,
                 String description,
                 Boolean allDay,
                 Date date,
                 Time startTime,
                 Time endTime,
                 TimeZone timeZone,
                 EventType eventType,
                 Color color,
                 List<Task> tasks,
                 List<TaskGroup> taskGroups) {
        this.title = title;
        this.description = description;
        this.allDay = allDay;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeZone = timeZone;
        this.eventType = eventType;
        this.color = color;
        this.tasks = tasks;
        this.taskGroups = taskGroups;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void addTaskGroup(TaskGroup taskGroup) {
        taskGroups.add(taskGroup);
    }

    public void removeTaskGroup(TaskGroup taskGroup) {
        taskGroups.remove(taskGroup);
    }
}

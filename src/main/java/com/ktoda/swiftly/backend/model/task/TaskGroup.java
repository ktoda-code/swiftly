package com.ktoda.swiftly.backend.model.task;

import com.ktoda.swiftly.backend.model.Status;
import com.ktoda.swiftly.backend.model.event.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task_groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Event event;
    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Status status;
    @OneToMany(mappedBy = "taskGroup")
    @EqualsAndHashCode.Exclude
    private List<Task> tasks = new ArrayList<>();

    public TaskGroup(String name, Priority priority, List<Task> tasks) {
        this.name = name;
        this.priority = priority;
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }
}

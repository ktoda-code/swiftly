package com.ktoda.swiftly.backend.model;

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
import java.util.List;

@Entity
@Table(name = "statuses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Color color;
    @ManyToOne
    private Board board;
    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private List<Task> tasks;
    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private List<TaskGroup> taskGroups;
    @Transient
    private Color defaultColor = new Color(148, 148, 148, 202);

    public Status(String name, Color color) {
        this.name = name;
        if (color == null) {
            this.color = defaultColor;
        }
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

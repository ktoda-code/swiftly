package com.ktoda.swiftly.backend.task;

import com.ktoda.swiftly.backend.status.Status;
import com.ktoda.swiftly.backend.event.Event;
import com.ktoda.swiftly.backend.property.Property;
import com.ktoda.swiftly.backend.taskgroup.TaskGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {
    /**
     * Task custom id generated with prefix 'T' followed by number, e.g. T120
     */
    @Id
    @GeneratedValue(generator = "task_id_gen", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
            name = "task_id_gen",
            type = TaskIdGenerator.class,
            parameters = {
                    @Parameter(name = "sequence_name", value = "task_id_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @SequenceGenerator(
            name = "task_id_sequence",
            sequenceName = "task_id_sequence",
            allocationSize = 1
    )
    private String id;
    private String title;
    private String description;
    private Boolean finished;
    @Temporal(TemporalType.DATE)
    private Date createdOn;
    @Temporal(TemporalType.DATE)
    private Date modifiedOn;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    private Timestamp startTime;
    private Timestamp endTime;
    @OneToMany
    @EqualsAndHashCode.Exclude
    private List<Task> subTasks;
    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Event event;
    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Status status;
    @ManyToOne
    @EqualsAndHashCode.Exclude
    private TaskGroup taskGroup;
    @OneToMany(
            mappedBy = "task",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    @EqualsAndHashCode.Exclude
    private List<Property> properties = new ArrayList<>();

    public Task(String title, String description, Priority priority, List<Property> properties) {
        this.title = title;
        this.description = description;
        this.createdOn = new Date(System.currentTimeMillis());
        this.priority = priority;
        this.properties = properties;
    }

    public void addProperty(Property property) {
        properties.add(property);
    }

    public void removeProperty(Property property) {
        properties.remove(property);
    }

    public void addSubTask(Task task) {
        subTasks.add(task);
    }

    public void removeSubTask(Task task) {
        subTasks.remove(task);
    }
}

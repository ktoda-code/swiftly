package com.ktoda.swiftly.backend.model.property;

import com.ktoda.swiftly.backend.model.Status;
import com.ktoda.swiftly.backend.model.event.Event;
import com.ktoda.swiftly.backend.model.task.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @Enumerated(EnumType.STRING)
    @NotNull
    private PropertyType propertyType;
    @ManyToOne
    @EqualsAndHashCode.Exclude
    private Task task;

    public Property(String name, PropertyType propertyType) {
        this.name = name;
        this.propertyType = propertyType;
    }
}

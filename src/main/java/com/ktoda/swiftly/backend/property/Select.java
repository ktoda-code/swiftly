package com.ktoda.swiftly.backend.property;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Entity
@Table(name = "selects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Select {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Color color;
    @ManyToOne
    private SelectProperty property;

    public Select(String name, Color color) {
        this.name = name;
        this.color = color;
    }
}

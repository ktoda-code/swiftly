package com.ktoda.swiftly.backend.model;

import com.ktoda.swiftly.backend.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private List<Status> statuses = new ArrayList<>(3);

    public Board(String name) {
        this.name = name;
        statuses.add(new Status("Backlog", null));
        statuses.add(new Status("In Progress", new Color(58, 87, 185, 218)));
        statuses.add(new Status("Done", new Color(47, 150, 81, 207)));
    }

    public void addStatus(Status status) {
        statuses.add(status);
    }

    public void removeStatus(Status status) {
        statuses.remove(status);
    }
}

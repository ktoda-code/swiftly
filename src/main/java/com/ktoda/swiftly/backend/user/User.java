package com.ktoda.swiftly.backend.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ktoda.swiftly.backend.board.Board;
import com.ktoda.swiftly.backend.event.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {
    /**
     * User custom id generated with prefix 'U' followed by number, e.g. U120
     */
    @Id
    @GeneratedValue(generator = "user_id_gen", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
            name = "user_id_gen",
            type = UserIdGenerator.class,
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_id_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence",
            allocationSize = 1
    )
    private String id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    private String email;
    @JsonIgnore
    private String password;
    @CreatedDate
    private LocalDate createdOn;
    @LastModifiedDate
    private LocalDate updatedOn;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.REMOVE
    )
    @EqualsAndHashCode.Exclude
    private List<Board> boards = new ArrayList<>();
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.REMOVE
    )
    @EqualsAndHashCode.Exclude
    private List<Event> events = new ArrayList<>();

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void addBoard(Board board) {
        boards.add(board);
        board.setUser(this);
    }

    public void removeBoard(Board board) {
        boards.remove(board);
        board.setUser(null);
    }

    public void addEvent(Event event) {
        events.add(event);
        event.setUser(this);
    }

    public void removeEvent(Event event) {
        events.remove(event);
        event.setUser(null);
    }

}

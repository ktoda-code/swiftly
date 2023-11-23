package com.ktoda.swiftly.backend.model.property;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class EmailProperty extends Property {
    @Email
    @NotNull
    private String email;

    public EmailProperty(String name, String email) {
        super(name, PropertyType.EMAIL);
        this.email = email;
    }
}

package com.ktoda.swiftly.backend.property;

import jakarta.persistence.Entity;
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
public class StringProperty extends Property {
    @NotNull
    private String value;

    public StringProperty(String name, String value) {
        super(name, PropertyType.STRING);
        this.value = value;
    }
}

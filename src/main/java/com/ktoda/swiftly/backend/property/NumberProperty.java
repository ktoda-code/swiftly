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
public class NumberProperty extends Property {
    @NotNull
    private Number value;

    public NumberProperty(String name, Number value) {
        super(name, PropertyType.NUMBER);
        this.value = value;
    }
}

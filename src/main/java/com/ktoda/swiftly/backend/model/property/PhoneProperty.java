package com.ktoda.swiftly.backend.model.property;

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
public class PhoneProperty extends Property {
    @NotNull
    private String phoneNumber;

    public PhoneProperty(String name, String phoneNumber) {
        super(name, PropertyType.PHONE);
        this.phoneNumber = phoneNumber;
    }
}

package com.ktoda.swiftly.backend.property;

import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class DateProperty extends Property {
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date date;

    public DateProperty(String name, Date date) {
        super(name, PropertyType.DATE);
        this.date = date;
    }
}

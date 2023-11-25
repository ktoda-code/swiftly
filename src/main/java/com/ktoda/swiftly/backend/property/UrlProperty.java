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
public class UrlProperty extends Property {
    @NotNull
    private String url;

    public UrlProperty(String name, String url) {
        super(name, PropertyType.URL);
        this.url = url;
    }
}

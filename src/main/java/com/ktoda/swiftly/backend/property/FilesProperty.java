package com.ktoda.swiftly.backend.property;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class FilesProperty extends Property {
    private List<String> base64files;

    public FilesProperty(String name, List<String> base64files) {
        super(name, PropertyType.FILES);
        this.base64files = base64files;
    }
}

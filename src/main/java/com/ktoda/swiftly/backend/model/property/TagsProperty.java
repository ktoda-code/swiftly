package com.ktoda.swiftly.backend.model.property;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TagsProperty extends Property {
    @OneToMany(mappedBy = "property")
    private List<Tag> tags = new ArrayList<>();

    public TagsProperty(String name, List<Tag> tags) {
        super(name, PropertyType.TAGS);
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }
}

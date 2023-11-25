package com.ktoda.swiftly.backend.property;

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
public class SelectProperty extends Property {
    @OneToMany
    private List<Select> selectValues = new ArrayList<>();

    public SelectProperty(String name, List<Select> selectValues) {
        super(name, PropertyType.SELECT);
        this.selectValues = selectValues;
    }

    public void addSelect(Select select) {
        selectValues.add(select);
    }

    public void removeSelect(Select select) {
        selectValues.remove(select);
    }
}

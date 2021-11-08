package com.example.carparts.unit;

import com.example.carparts.model.NamedEntity;

import javax.persistence.*;

@Entity
@Table(name = "unit", schema = "warehouse")
public class UnitEntity extends NamedEntity {

    @Column(length = 30)
    private String name;

    @Column(name = "type", nullable = false, length = 1)
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

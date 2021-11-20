package cz.uhk.fim.warehouse.unit;

import cz.uhk.fim.warehouse.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "unit", schema = "warehouse")
public class UnitEntity extends BaseEntity {

    @Column(name = "name", nullable = false, length = 30)
    @NotEmpty
    private String name;

    @Column(name = "type", nullable = false, length = 1)
    @NotEmpty
    private String type;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

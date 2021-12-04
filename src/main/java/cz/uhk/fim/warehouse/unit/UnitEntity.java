package cz.uhk.fim.warehouse.unit;

import cz.uhk.fim.warehouse.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "unit", schema = "warehouse")
public class UnitEntity extends BaseEntity {

    @Column(name = "name", nullable = false, length = 30)
    @Size(min = 2, max = 30, message = "Pole musí obsahovat 2-30 znaků")
    private String name;

    @Column(name = "type", nullable = false, length = 1)
    //@NotEmpty
    private Character type;

    public UnitEntity (){

    }

    public UnitEntity (Character type){
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getType() {
        return this.type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

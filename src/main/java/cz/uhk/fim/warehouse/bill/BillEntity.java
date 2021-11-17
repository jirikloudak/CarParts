package cz.uhk.fim.warehouse.bill;

import cz.uhk.fim.warehouse.model.BaseEntity;
import cz.uhk.fim.warehouse.unit.UnitEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Entity
@Table(name = "bill", schema = "warehouse")
public class BillEntity extends BaseEntity {

    @Column(name = "type", nullable = false)
    @NotEmpty
    private Character type;

    @Column(name = "paired", nullable = false, length = 25)
    @NotEmpty
    private String paired;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private UnitEntity unit;

    @Column(name = "bill_date", nullable = false)
    @NotEmpty
    private Date billDate;

    public BillEntity() {
    }

    public BillEntity(Character type) {
        this.type = type;
    }

    public Character getType() {
        return this.type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public String getPaired() {
        return this.paired;
    }

    public void setPaired(String paired) {
        this.paired = paired;
    }

    public UnitEntity getUnit() {
        return this.unit;
    }

    public void setUnit(UnitEntity unit) {
        this.unit = unit;
    }

    public Date getBillDate() {
        return this.billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
}

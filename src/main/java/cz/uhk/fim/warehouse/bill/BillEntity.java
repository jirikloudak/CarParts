package cz.uhk.fim.warehouse.bill;

import cz.uhk.fim.warehouse.model.BaseEntity;
import cz.uhk.fim.warehouse.unit.UnitEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "bill", schema = "warehouse")
public class BillEntity extends BaseEntity {

    @Column(name = "type", nullable = false)
    //@NotEmpty
    private Character type;

    @Column(name = "paired", nullable = false, length = 25)
    @Size(min = 3, max = 25, message = "Pole musí obsahovat 3-25 znaků")
    private String paired;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private UnitEntity unit;

    @Column(name = "bill_date", nullable = false)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate billDate;

    public BillEntity() {

    }

    public BillEntity(Character type, LocalDate billDate) {
        this.type = type;
        this.billDate = billDate;
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

    public LocalDate getBillDate() {
        return this.billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }
}

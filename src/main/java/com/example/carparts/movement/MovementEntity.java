package com.example.carparts.movement;

import com.example.carparts.bill.BillEntity;
import com.example.carparts.model.BaseEntity;
import com.example.carparts.part.PartEntity;
import com.example.carparts.unit.UnitEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "movement", schema = "warehouse")
public class MovementEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private BillEntity bill;

    @ManyToOne
    @JoinColumn(name = "part_id", nullable = false)
    private PartEntity part;

    @Column(name = "qty", nullable = false)
    @NotEmpty
    @Min(value = 1, message = "Počet kusů musí být kladný")
    private Integer qty;

    public BillEntity getBill() {
        return this.bill;
    }

    public void setBill(BillEntity bill) {
        this.bill = bill;
    }

    public PartEntity getPart() {
        return this.part;
    }

    public void setPart(PartEntity part) {
        this.part = part;
    }

    public Integer getQty() {
        return this.qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}

package com.example.carparts.movement;

import javax.persistence.*;

@Entity
@Table(name = "movement", schema = "warehouse")
public class MovementEntity {
    private int id;
    private int qty;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "qty", nullable = false)
    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovementEntity that = (MovementEntity) o;

        if (id != that.id) return false;
        if (qty != that.qty) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + qty;
        return result;
    }
}

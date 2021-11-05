package com.example.carparts.price;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "price", schema = "warehouse")
public class PriceEntity {
    private int id;
    private String name;
    private BigDecimal purchase;
    private BigDecimal sale;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 6)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "purchase", nullable = false, precision = 2)
    public BigDecimal getPurchase() {
        return purchase;
    }

    public void setPurchase(BigDecimal purchase) {
        this.purchase = purchase;
    }

    @Basic
    @Column(name = "sale", nullable = false, precision = 2)
    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceEntity that = (PriceEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (purchase != null ? !purchase.equals(that.purchase) : that.purchase != null) return false;
        if (sale != null ? !sale.equals(that.sale) : that.sale != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (purchase != null ? purchase.hashCode() : 0);
        result = 31 * result + (sale != null ? sale.hashCode() : 0);
        return result;
    }
}

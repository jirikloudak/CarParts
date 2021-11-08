package com.example.carparts.price;

import com.example.carparts.model.NamedEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "price", schema = "warehouse")
public class PriceEntity extends NamedEntity {

    @Column(length = 6)
    private String name;

    @Column(name = "purchase", nullable = false, precision = 2)
    private BigDecimal purchase;

    @Column(name = "sale", nullable = false, precision = 2)
    private BigDecimal sale;

    public BigDecimal getPurchase() {
        return this.purchase;
    }

    public void setPurchase(BigDecimal purchase) {
        this.purchase = purchase;
    }

    public BigDecimal getSale() {
        return this.sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }
}

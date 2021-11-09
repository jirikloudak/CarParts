package com.example.carparts.price;

import com.example.carparts.model.NamedEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Entity
@Table(name = "price", schema = "warehouse")
public class PriceEntity extends NamedEntity {

    @Column(length = 6)
    @NotEmpty
    @Pattern(regexp = "^\\d\\d[A-Za-z]$", message = "Cenová skupina se musí skládat ze dvou číslic a písmena, např. \"12B\"")
    private String name;

    @Column(name = "purchase", nullable = false, precision = 2)
    @NotEmpty
    @Min(value = 0, message = "Nákupní cena musí být nezáporná")
    private BigDecimal purchase;

    @Column(name = "sale", nullable = false, precision = 2)
    @NotEmpty
    @Min(value = 0, message = "Prodejní cena musí být nezáporná")
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

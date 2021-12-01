package cz.uhk.fim.warehouse.price;

import cz.uhk.fim.warehouse.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Entity
@Table(name = "price", schema = "warehouse")
public class PriceEntity extends BaseEntity {

    @Column(name = "name", nullable = false, length = 6)
    @Pattern(regexp = "^\\d\\d[A-Za-z]$", message = "Musí se skládat ze dvou číslic a písmena, např. \"12B\"")
    private String name;

    @Column(name = "purchase", nullable = false, precision = 2)
    @Min(value = 0, message = "Cena musí být nezáporná")
    private BigDecimal purchase;

    @Column(name = "sale", nullable = false, precision = 2)
    @Min(value = 0, message = "Cena musí být nezáporná")
    private BigDecimal sale;

    public PriceEntity (){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return this.name;
    }
}

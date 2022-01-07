package cz.uhk.fim.warehouse.part;

import cz.uhk.fim.warehouse.model.BaseEntity;
import cz.uhk.fim.warehouse.price.PriceEntity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "part", schema = "warehouse")
public class PartEntity extends BaseEntity {

    @Column(name = "name", nullable = false, length = 50)
    @Size(min = 2, max = 50, message = "Pole musí obsahovat 2-50 znaků")
    private String name;

    @Column(name = "code", nullable = false, length = 20)
    @Size(min = 1, max = 20, message = "Pole musí obsahovat 2-20 znaků")
    private String code;

    @ManyToOne
    @JoinColumn(name = "price_id", nullable = false)
    private PriceEntity price;

    @Column(name = "qty", nullable = false)
    private Integer qty;

    @Column(name = "min", nullable = false)
    @Min(value = 0, message = "Minimum musí být nezáporné")
    private Integer min;

    public PartEntity (){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PriceEntity getPrice() {
        return this.price;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }

    public Integer getQty() {
        return this.qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getMin() {
        return this.min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return this.code + ": " + this.name;
    }
}

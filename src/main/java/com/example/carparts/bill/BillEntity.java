package com.example.carparts.bill;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "bill", schema = "warehouse")
public class BillEntity {
    private int id;
    private String type;
    private String paired;
    private Date billDate;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 1)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "paired", nullable = false, length = 25)
    public String getPaired() {
        return paired;
    }

    public void setPaired(String paired) {
        this.paired = paired;
    }

    @Basic
    @Column(name = "bill_date", nullable = false)
    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillEntity that = (BillEntity) o;

        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (paired != null ? !paired.equals(that.paired) : that.paired != null) return false;
        if (billDate != null ? !billDate.equals(that.billDate) : that.billDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (paired != null ? paired.hashCode() : 0);
        result = 31 * result + (billDate != null ? billDate.hashCode() : 0);
        return result;
    }
}

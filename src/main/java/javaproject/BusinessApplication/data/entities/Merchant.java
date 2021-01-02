package javaproject.BusinessApplication.data.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Merchant extends User {


    private List<Customer> customers;
    private BigDecimal salesValue;


    public Merchant() {
    }

    @OneToMany( mappedBy = "merchant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public BigDecimal getSalesValue() {
        return salesValue;
    }

    public void setSalesValue(BigDecimal salesValue) {
        this.salesValue = salesValue;
    }
}


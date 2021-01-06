package javaproject.BusinessApplication.data.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity{
    private String name;
    private String address;
    private String phoneNumber;
    private BigDecimal moneySpend;

    public Customer() {
        this.moneySpend=new BigDecimal(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getMoneySpend() {
        return moneySpend;
    }

    public void setMoneySpend(BigDecimal moneySpend) {
        this.moneySpend = moneySpend;
    }


    @Override
    public String toString() {
        return "Customer: " + this.getName() +
                "\n     Address: " + this.getAddress() +
                "\n     Phone number: " + this.getPhoneNumber() +
                "\n     Money spend: " + this.getMoneySpend();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return name.equals(customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

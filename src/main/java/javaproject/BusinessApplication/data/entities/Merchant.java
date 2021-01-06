package javaproject.BusinessApplication.data.entities;

import javaproject.BusinessApplication.exeptions.EntityAlreadyExistsException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Merchant extends User {


    private BigDecimal salesValue;


    public Merchant() {
        salesValue=new BigDecimal(0);
    }


    public BigDecimal getSalesValue() {
        return salesValue;
    }

    public void setSalesValue(BigDecimal salesValue) {
        this.salesValue = salesValue;
    }

    @Override
    public String toString() {
        return "Merchant: " + this.getUsername() +
                "\n     First name: " + this.getFirstName() +
                "\n     Last name: " + this.getLastName() +
                "\n     Sales value: " + this.getSalesValue() +
                "\n";
    }
}


package javaproject.BusinessApplication.data.entities;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    private String type;
    private String model;
    private int quantity;
    private BigDecimal price;

    public Product() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

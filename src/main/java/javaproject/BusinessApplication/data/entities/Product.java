package javaproject.BusinessApplication.data.entities;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    private String type;
    private String model;
    private int quantity;
    private BigDecimal price;
    private String addedBy;

    public Product(String type, String model, int quantity, BigDecimal price, String addedBy) {
        this.type = type;
        this.model = model;
        this.quantity = quantity;
        this.price = price;
        this.addedBy = addedBy;
    }

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

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return type.equals(product.type) && model.equals(product.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, model);
    }

    @Override
    public String toString(){
        return "Product: " + this.getType() +
                "\n     Model: " + this.getModel() +
                "\n     Quantity: " + this.getQuantity() +
                "\n     Price: " + this.getPrice() +
                "\n";
    }
}

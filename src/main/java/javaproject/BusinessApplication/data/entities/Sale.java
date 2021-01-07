package javaproject.BusinessApplication.data.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Sales")
public class Sale extends BaseEntity{

    private String productTypeAndModel;
    private String buyerName;
    private String sellerName;
    private int quantity;
    private BigDecimal atPrice;
    private BigDecimal totalPrice;
    private Date date;

    public Sale() {
    }

    public Sale(String productTypeAndModel, String buyerName, String sellerName, int quantity, BigDecimal atPrice) {
        this.productTypeAndModel = productTypeAndModel;
        this.buyerName = buyerName;
        this.sellerName = sellerName;
        this.quantity = quantity;
        this.atPrice = atPrice;
        this.totalPrice=atPrice.multiply(BigDecimal.valueOf(quantity));
        this.date=new Date();
    }

    public String getProductTypeAndModel() {
        return productTypeAndModel;
    }

    public void setProductTypeAndModel(String productTypeAndModel) {
        this.productTypeAndModel = productTypeAndModel;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAtPrice() {
        return atPrice;
    }

    public void setAtPrice(BigDecimal atPrice) {
        this.atPrice = atPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Sale: " +
                "\n     Product type and model: " + this.getProductTypeAndModel() +
                "\n     Quantity: " + this.getQuantity() +
                "\n     Price: " + this.getAtPrice() +
                "\n     Customer name: " + this.getBuyerName() +
                "\n     Merchant username: " + this.getSellerName() +
                "\n     Total price: " + this.getTotalPrice() +
                "\n";
    }
}

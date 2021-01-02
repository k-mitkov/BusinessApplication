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
}

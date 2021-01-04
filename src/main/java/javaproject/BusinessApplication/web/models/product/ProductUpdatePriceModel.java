package javaproject.BusinessApplication.web.models.product;

import java.math.BigDecimal;

public class ProductUpdatePriceModel {
    private String type;
    private String model;
    private BigDecimal price;

    public ProductUpdatePriceModel() {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

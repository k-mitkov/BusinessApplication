package javaproject.BusinessApplication.web.models.product;

public class ProductUpdateQuantityModel {

    private String type;
    private String model;
    private int quantity;

    public ProductUpdateQuantityModel() {
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
}

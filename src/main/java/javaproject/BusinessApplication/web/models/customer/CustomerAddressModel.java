package javaproject.BusinessApplication.web.models.customer;

public class CustomerAddressModel {
    private String name;
    private String address;

    public CustomerAddressModel() {
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
}

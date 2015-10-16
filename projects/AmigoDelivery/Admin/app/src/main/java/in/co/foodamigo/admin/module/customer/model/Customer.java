package in.co.foodamigo.admin.module.customer.model;

import java.io.Serializable;
import java.util.List;

public class Customer implements Serializable {

    private String name;
    private List<Address> address;
    private String mobileNumber;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public Customer(String name, String mobileNumber) {
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public Customer(String name, List<Address> address, String mobileNumber) {
        this.name = name;
        this.address = address;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}

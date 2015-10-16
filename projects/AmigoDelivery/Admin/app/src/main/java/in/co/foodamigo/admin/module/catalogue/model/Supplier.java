package in.co.foodamigo.admin.module.catalogue.model;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

import in.co.foodamigo.admin.module.customer.model.Address;

@Table
public class Supplier implements Serializable {

    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "address_id")
    private Address address;
    @Column(name = "mobileNumber")
    private String mobileNumber;

    public Supplier() {
    }

    public Supplier(long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}

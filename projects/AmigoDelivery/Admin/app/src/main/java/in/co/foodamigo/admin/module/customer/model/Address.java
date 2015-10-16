package in.co.foodamigo.admin.module.customer.model;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class Address implements Serializable {

    private long id;
    @Column(name = "add_line_one")
    private String addLineOne;
    @Column(name = "locality")
    private String locality;
    @Column(name = "location_id")
    private Location location;

    public Address() {
    }

    public Address(String addLineOne, String addLineTwo, String locality) {
        this.addLineOne = addLineOne;
        this.locality = locality;
    }

    public Address(Location location) {
        this.location = location;
    }

    public Address(String addLineOne, String addLineTwo, String locality, Location location) {
        this.addLineOne = addLineOne;
        this.locality = locality;
        this.location = location;
    }

    public String getAddLineOne() {
        return addLineOne;
    }

    public void setAddLineOne(String addLineOne) {
        this.addLineOne = addLineOne;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

package model.common;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

@Table
public class Location implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "admin_unit_id")
    private AdminUnit adminUnit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public AdminUnit getAdminUnit() {
        return adminUnit;
    }

    public void setAdminUnit(AdminUnit adminUnit) {
        this.adminUnit = adminUnit;
    }
}

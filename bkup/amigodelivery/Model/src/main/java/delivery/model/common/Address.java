package delivery.model.common;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

@Table
public class Address {

    private long id;
    @Column(name = "addressString")
    private String addressString;
    @Column(name = "locality")
    private String locality;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;

    public Address() {
    }

    public Address(String addressString, String locality) {
        this.addressString = addressString;
        this.locality = locality;
    }

    public Address(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Address(String addressString, String locality, double latitude, double longitude) {
        this.addressString = addressString;
        this.locality = locality;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (Double.compare(address.latitude, latitude) != 0) return false;
        if (Double.compare(address.longitude, longitude) != 0) return false;
        if (addressString != null ? !addressString.equals(address.addressString) : address.addressString != null)
            return false;
        return !(locality != null ? !locality.equals(address.locality) : address.locality != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = addressString != null ? addressString.hashCode() : 0;
        result = 31 * result + (locality != null ? locality.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String getAddressString() {
        return addressString;
    }

    public void setAddressString(String addressString) {
        this.addressString = addressString;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

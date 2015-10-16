package delivery.model.common;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

@Table
public class Address {

    @Column(name = "addressString")
    private String addressString;
    @Column(name = "locality")
    private String locality;
    @Column(name = "geolocation_id")
    private GeoLocation location;

    public Address() {
    }

    public Address(String addressString, String locality, GeoLocation location) {
        this.addressString = addressString;
        this.locality = locality;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressString='" + addressString + '\'' +
                ", locality='" + locality + '\'' +
                ", location=" + location +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (addressString != null ? !addressString.equals(address.addressString) : address.addressString != null)
            return false;
        if (locality != null ? !locality.equals(address.locality) : address.locality != null)
            return false;
        return !(location != null ? !location.equals(address.location) : address.location != null);

    }

    @Override
    public int hashCode() {
        int result = addressString != null ? addressString.hashCode() : 0;
        result = 31 * result + (locality != null ? locality.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
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

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }

}

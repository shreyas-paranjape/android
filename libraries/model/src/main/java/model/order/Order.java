package model.order;

import android.databinding.ObservableDouble;
import android.databinding.ObservableInt;

import com.orm.dsl.Column;
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.common.Location;
import model.party.Party;

@Table(name = "ordr")
public class Order implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;
    @Column(name = "party_id")
    private Party party;
    @Column(name = "code")
    private String code;
    @Column(name = "delivery_location_id")
    private Location deliveryLocation;
    @Column(name = "placed_at")
    private Date placedAt;
    @Column(name = "status")
    private String status;

    @Ignore
    private List<OrderItem> orderItems = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return !(code != null ? !code.equals(order.code) : order.code != null);

    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public Location getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(Location location) {
        this.deliveryLocation = location;
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

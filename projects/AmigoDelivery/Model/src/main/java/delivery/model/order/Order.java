package delivery.model.order;

import android.databinding.ObservableDouble;
import android.databinding.ObservableInt;

import com.orm.dsl.Column;
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

import java.util.ArrayList;
import java.util.List;

import delivery.model.profile.Party;

@Table(name = "Ordr")
public class Order {

    private long id;
    @Column(name = "party_id")
    private Party party;
    @Column(name = "code")
    private String code;

    @Ignore
    private List<OrderItem> orderItems = new ArrayList<>();
    @Ignore
    public final ObservableDouble total = new ObservableDouble();
    @Ignore
    public final ObservableInt cartSize = new ObservableInt();

    public Order() {
    }

    public Order(long id) {
        this.id = id;
    }

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
}

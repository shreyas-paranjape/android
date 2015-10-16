package in.co.foodamigo.admin.module.order.model;

import android.databinding.ObservableDouble;
import android.databinding.ObservableInt;

import com.orm.dsl.Column;
import com.orm.dsl.Ignore;
import com.orm.dsl.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Order")
public class Order implements Serializable {

    @Column(name = "id")
    private long id;
    private String code;
    @Ignore
    private List<OrderItem> orderItems = new ArrayList<>();

    public final ObservableDouble total = new ObservableDouble();
    public final ObservableInt quantity = new ObservableInt();


    public Order() {
    }

    public Order(long id) {
        this.id = id;
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
}

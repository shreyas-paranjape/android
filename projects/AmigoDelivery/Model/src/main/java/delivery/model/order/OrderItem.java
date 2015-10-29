package delivery.model.order;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

import delivery.model.catalogue.ProductParty;

@Table
public class OrderItem implements Serializable {

    private long id;
    @Column(name = "order_id")
    private Order order;
    @Column(name = "product_party_id")
    private ProductParty productParty;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;

    public OrderItem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductParty getProductParty() {
        return productParty;
    }

    public void setProductParty(ProductParty productParty) {
        this.productParty = productParty;
    }
}

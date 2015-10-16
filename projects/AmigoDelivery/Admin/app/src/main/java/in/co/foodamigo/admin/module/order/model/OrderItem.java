package in.co.foodamigo.admin.module.order.model;

import com.orm.dsl.Column;
import com.orm.dsl.Table;

import java.io.Serializable;

import in.co.foodamigo.admin.module.catalogue.model.Product;

@Table
public class OrderItem implements Serializable {

    private long id;
    @Column(name = "order_id")
    private Order order;
    @Column(name = "product_id")
    private Product product;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;

    public OrderItem() {
    }

    public OrderItem(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

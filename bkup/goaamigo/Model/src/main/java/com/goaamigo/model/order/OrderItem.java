package com.goaamigo.model.order;

import com.goaamigo.model.catalogue.Product;
import com.orm.dsl.Column;

import java.io.Serializable;

public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1l;

    private long id;

    @Column(name = "order_id")
    private Order order;

    @Column(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "status")
    private String status;

}

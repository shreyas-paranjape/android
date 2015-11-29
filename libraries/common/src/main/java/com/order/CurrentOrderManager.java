package com.order;

import android.databinding.ObservableDouble;
import android.databinding.ObservableInt;
import android.util.Log;

import java.util.List;

import de.greenrobot.event.EventBus;
import model.catalogue.Product;
import model.order.Order;
import model.order.OrderItem;

public class CurrentOrderManager {

    private Order order;
    public final ObservableDouble total = new ObservableDouble();
    public final ObservableInt size = new ObservableInt();
    private final EventBus eventBus = EventBus.getDefault();

    public CurrentOrderManager() {
        order = new Order();
        eventBus.register(this, 1);
    }

    public Order getOrder() {
        return order;
    }

    public void modifyItem(Product product, int quantity) {
        if (productValid(product)) {
            OrderItem orderItem = getItemForProduct(product);
            if (orderItem == null) {
                orderItem = new OrderItem();
                orderItem.setProduct(product);
                orderItem.setOrder(order);
                order.getOrderItems().add(orderItem);
            }
            changeProductQuantity(orderItem, quantity);
            changeTotal();
            Log.d("Order Manager", "Cart size" + cartSize());
            size.set(cartSize());
        } else {
            Log.e("Order Manager", "Bad product : " + product);
        }

    }

    private boolean productValid(Product product) {
        boolean isValid = true;
        if (product.getDetail() == null ||
                product.getDetail().getPrice() <= 0) {
            isValid = false;
        }
        return isValid;
    }

    public int cartSize() {
        final List<OrderItem> orderItems = order.getOrderItems();
        int size = 0;
        for (OrderItem item : orderItems) {
            size += item.getQuantity();
        }
        return size;
    }

    public void checkOut() {
        eventBus.post(new CheckOutEvent(order));
    }

    private OrderItem getItemForProduct(Product product) {
        final List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem item : orderItems) {
            if (item.getProduct().getId() == product.getId()) {
                return item;
            }
        }
        return null;
    }


    private void changeProductQuantity(OrderItem productOrderItem, int quantity) {
        productOrderItem.setQuantity(productOrderItem.getQuantity() + quantity);
        if (productOrderItem.getQuantity() <= 0) {
            order.getOrderItems().remove(productOrderItem);
        } else {
            productOrderItem.setPrice(
                    productOrderItem.getProduct().getDetail().getPrice() * productOrderItem.getQuantity());
        }
    }


    private void changeTotal() {
        final List<OrderItem> orderItems = order.getOrderItems();
        double t = 0;
        for (OrderItem orderItem : orderItems) {
            t += orderItem.getPrice();
        }
        total.set(t);
    }

    @SuppressWarnings("unused")
    public void onEvent(ModifyCartEvent event) {
        switch (event.getAction()) {
            case ADD:
                modifyItem(event.getProduct(), 1);
                break;
            case REMOVE:
                modifyItem(event.getProduct(), -1);
                break;
        }
        eventBus.post(new CartModifiedEvent(cartSize()));
    }

    public static class CartModifiedEvent {
        private final int cartSize;

        public CartModifiedEvent(int cartSize) {
            this.cartSize = cartSize;
        }

        public int getCartSize() {
            return cartSize;
        }
    }

    public enum CartAction {
        ADD() {},
        REMOVE() {}
    }

    public static class ModifyCartEvent {
        private final Product product;
        private final CartAction action;

        public ModifyCartEvent(Product product, CartAction action) {
            this.product = product;
            this.action = action;
        }

        public Product getProduct() {
            return product;
        }

        public CartAction getAction() {
            return action;
        }
    }

    public static class CheckOutEvent {
        private final Order order;


        public CheckOutEvent(Order order) {
            this.order = order;
        }

        public Order getOrder() {
            return order;
        }
    }
}


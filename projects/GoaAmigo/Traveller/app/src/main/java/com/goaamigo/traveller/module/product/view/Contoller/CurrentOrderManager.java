package com.goaamigo.traveller.module.product.view.Contoller;

import android.os.Bundle;

import com.event.ChangeContentEvent;
import com.goaamigo.traveller.module.app.singleton.Constant;
import com.goaamigo.traveller.module.product.view.activity.CheckoutActivity;
import com.goaamigo.traveller.module.product.view.fragment.OrderStatusFragment;

import java.util.Date;
import java.util.List;

import de.greenrobot.event.EventBus;
import model.catalogue.Product;
import model.common.Location;
import model.order.Order;
import model.order.OrderItem;

public class CurrentOrderManager {

    private Order order;
    private final EventBus eventBus = EventBus.getDefault();

    public CurrentOrderManager() {
        order = new Order();
        eventBus.register(this, 1);
    }

    public Order getOrder() {
        return order;
    }

    public void modifyItem(Product product, int quantity) {
        OrderItem orderItem = getItemForProduct(product);
        if (orderItem == null) {
            orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        changeProductQuantity(orderItem, quantity);
        changeTotal();
        order.cartSize.set(order.cartSize.get() + quantity);
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
        // if logged in goto address selection page
        // if not logged in show add profile
        eventBus.post(
                new ChangeContentEvent(ChangeContentEvent.ContentType.ACTIVITY, new Bundle()) {
                    @Override
                    public Class getContentClass() {
                        return CheckoutActivity.class;
                    }
                });
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
                    productOrderItem.getProduct().getPrice() * productOrderItem.getQuantity());
        }
    }


    private void changeTotal() {
        final List<OrderItem> orderItems = order.getOrderItems();
        double total = 0;
        for (OrderItem orderItem : orderItems) {
            total += orderItem.getPrice();
        }
        order.total.set(total);
    }

    public Location getDeliveryAddress() {
        return order.getDeliveryLocation();
    }

    public void setDeliveryAddress(Location deliveryAddress) {
        order.setDeliveryLocation(deliveryAddress);
    }


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

    public void placeOrder() {
        order.setPlacedAt(new Date());
        order.setStatus("PENDING");
        Bundle data = new Bundle();
        data.putSerializable(Constant.ORDER, order);
        //TODO Send order to server
        //TODO Save order
        eventBus.post(
                new ChangeContentEvent(
                        ChangeContentEvent.ContentType.FRAGMENT,
                        data) {
                    @Override
                    public Class getContentClass() {
                        return OrderStatusFragment.class;
                    }
                });
        //TODO order = new Order();
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

}


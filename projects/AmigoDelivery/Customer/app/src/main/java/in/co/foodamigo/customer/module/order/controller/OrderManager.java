package in.co.foodamigo.customer.module.order.controller;

import android.os.Bundle;

import java.util.List;

import de.greenrobot.event.EventBus;
import delivery.model.catalogue.ProductParty;
import delivery.model.common.Address;
import delivery.model.order.Order;
import delivery.model.order.OrderItem;
import in.co.foodamigo.customer.module.app.view.event.ChangeContentEvent;
import in.co.foodamigo.customer.module.order.view.component.CheckoutActivity;

public class OrderManager {

    private final Order order;
    private Address deliveryAddress;

    public OrderManager() {
        order = new Order();
        EventBus.getDefault().register(this, 1);
    }

    public Order getOrder() {
        return order;
    }

    public void modifyItem(ProductParty productParty, int quantity) {
        OrderItem orderItem = getItemForProduct(productParty);
        if (orderItem == null) {
            orderItem = new OrderItem();
            orderItem.setProductParty(productParty);
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
        EventBus.getDefault().post(
                new ChangeContentEvent(ChangeContentEvent.ContentType.ACTIVITY, new Bundle()) {
                    @Override
                    public Class getContentClass() {
                        return CheckoutActivity.class;
                    }
                });
    }


    public void onEvent(ModifyCartEvent event) {
        switch (event.getAction()) {
            case ADD:
                modifyItem(event.getProductParty(), 1);
                break;
            case REMOVE:
                modifyItem(event.getProductParty(), -1);
                break;
        }
        EventBus.getDefault().post(new CartModifiedEvent(cartSize()));
    }

    private OrderItem getItemForProduct(ProductParty productParty) {
        final List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem item : orderItems) {
            if (item.getProductParty().getId() == productParty.getId()) {
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
                    productOrderItem.getProductParty().getRate() * productOrderItem.getQuantity());
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

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
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
        private final ProductParty productParty;
        private final CartAction action;

        public ModifyCartEvent(ProductParty productParty, CartAction action) {
            this.productParty = productParty;
            this.action = action;
        }

        public ProductParty getProductParty() {
            return productParty;
        }

        public CartAction getAction() {
            return action;
        }
    }

    public static class CheckOutEvent {
        private final long orderId;

        public CheckOutEvent(long orderId) {
            this.orderId = orderId;
        }

        public long getOrderId() {
            return orderId;
        }
    }
}

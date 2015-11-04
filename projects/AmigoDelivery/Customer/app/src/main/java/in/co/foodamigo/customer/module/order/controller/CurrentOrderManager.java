package in.co.foodamigo.customer.module.order.controller;

import android.os.Bundle;

import com.event.ChangeContentEvent;

import java.util.Date;
import java.util.List;

import de.greenrobot.event.EventBus;
import delivery.model.catalogue.ProductParty;
import delivery.model.common.Address;
import delivery.model.order.Order;
import delivery.model.order.OrderItem;
import in.co.foodamigo.customer.module.app.singleton.Constant;
import in.co.foodamigo.customer.module.order.view.component.CheckoutActivity;
import in.co.foodamigo.customer.module.order.view.component.OrderStatusFragment;

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
        eventBus.post(
                new ChangeContentEvent(ChangeContentEvent.ContentType.ACTIVITY, new Bundle()) {
                    @Override
                    public Class getContentClass() {
                        return CheckoutActivity.class;
                    }
                });
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
        return order.getDeliveryAddress();
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        order.setDeliveryAddress(deliveryAddress);
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

}

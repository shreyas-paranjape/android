package com.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.List;

import de.greenrobot.event.EventBus;
import model.catalogue.Product;
import model.order.OrderItem;

public abstract class CartItemAdapter extends ArrayAdapter<OrderItem> {

    protected final LayoutInflater inflater;

    public CartItemAdapter(Context context, int resource, List<OrderItem> orderItems) {
        super(context, resource, orderItems);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    protected void addItem(Product product) {
        modifyItem(product, CurrentOrderManager.CartAction.ADD);
    }

    protected void removeItem(Product product) {
        modifyItem(product, CurrentOrderManager.CartAction.REMOVE);
    }

    protected void modifyItem(Product product, CurrentOrderManager.CartAction action) {
        EventBus.getDefault().post(
                new CurrentOrderManager.ModifyCartEvent(product, action));
    }
}

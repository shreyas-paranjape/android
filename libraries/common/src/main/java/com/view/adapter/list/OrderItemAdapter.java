package com.view.adapter.list;

import android.content.Context;

import com.order.CurrentOrderManager;

import java.util.List;

import de.greenrobot.event.EventBus;
import model.catalogue.Product;
import model.order.OrderItem;

@SuppressWarnings("unused")
public abstract class OrderItemAdapter extends AbstractListAdapter<OrderItem> {

    public OrderItemAdapter(Context context, List<OrderItem> orderItems) {
        super(context, orderItems);
        EventBus.getDefault().register(this);
    }

    public void onEvent(CurrentOrderManager.ModifyCartEvent event) {
        notifyDataSetChanged();
    }

    protected void addItem(Product product) {
        modifyItem(product, CurrentOrderManager.CartAction.ADD);
    }

    protected void removeItem(Product product) {
        modifyItem(product, CurrentOrderManager.CartAction.REMOVE);
    }

    protected void modifyItem(Product product, CurrentOrderManager.CartAction action) {
        EventBus.getDefault().post(new CurrentOrderManager.ModifyCartEvent(product, action));
    }

}

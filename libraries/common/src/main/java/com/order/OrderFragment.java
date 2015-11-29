package com.order;

import android.os.Bundle;

import com.view.adapter.list.OrderItemAdapter;
import com.view.fragment.AbstractFragment;

public abstract class OrderFragment extends AbstractFragment {

    protected CurrentOrderManager currentOrderManager;
    protected OrderItemAdapter cartItemsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentOrderManager = getOrderManager();
        cartItemsAdapter = getAdapter();
    }

    protected abstract CurrentOrderManager getOrderManager();

    protected abstract OrderItemAdapter getAdapter();

}

package com.order;

import android.app.Fragment;
import android.os.Bundle;

import de.greenrobot.event.EventBus;

public abstract class OrderFragment extends Fragment {

    protected CurrentOrderManager currentOrderManager;
    protected CartItemAdapter cartItemsAdapter;


    public OrderFragment() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentOrderManager = getOrderManager();
        cartItemsAdapter = getAdapter();
    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    public void onEvent(CurrentOrderManager.ModifyCartEvent event) {
        cartItemsAdapter.notifyDataSetChanged();
    }

    protected abstract CurrentOrderManager getOrderManager();

    protected abstract CartItemAdapter getAdapter();

}

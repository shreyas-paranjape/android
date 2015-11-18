package com.goaamigo.traveller.module.product.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goaamigo.traveller.databinding.FragmentOrderBinding;
import com.goaamigo.traveller.module.app.singleton.TravellerApp;
import com.goaamigo.traveller.module.product.view.adapter.ProductCartItemAdapter;
import com.order.CurrentOrderManager;
import com.order.OrderFragment;

public class ProductOrderFragment extends OrderFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentOrderBinding rootBinding = FragmentOrderBinding.inflate(inflater);
        initView(rootBinding);
        return rootBinding.getRoot();
    }


    private void initView(FragmentOrderBinding rootBinding) {
        rootBinding.lvCartItems.setAdapter(cartItemsAdapter);
        rootBinding.setOrderManager(currentOrderManager);
        rootBinding.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentOrderManager.checkOut();
            }
        });
    }

    protected CurrentOrderManager getOrderManager() {
        return ((TravellerApp) getActivity()
                .getApplication())
                .getCurrentOrderManager();
    }

    @NonNull
    protected ProductCartItemAdapter getAdapter() {
        return new ProductCartItemAdapter(
                getActivity(),
                currentOrderManager.getOrder().getOrderItems());
    }
}

package com.goaamigo.traveller.module.product.view.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.singleton.TravellerApp;
import com.goaamigo.traveller.module.product.view.Contoller.CurrentOrderManager;
import com.goaamigo.traveller.module.product.view.adapter.CartItemAdapter;

import de.greenrobot.event.EventBus;

public class OrderFragment extends Fragment {
    TextView cartSize;
    TextView cartTotal;
    Button checkout;

    private CurrentOrderManager currentOrderManager;
    private ArrayAdapter cartItemsAdapter;

    public OrderFragment() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initCart();
        //FragmentOrderBinding rootBinding = FragmentOrderBinding.inflate(inflater);
        View v = inflater.inflate(R.layout.fragment_order, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        cartSize = (TextView) v.findViewById(R.id.tv_cart_size);
        cartTotal = (TextView) v.findViewById(R.id.tv_order_total);
        checkout = (Button) v.findViewById(R.id.btn_checkout);
        ListView lvCartItems = (ListView) v.findViewById(R.id.lv_cart_items);
        cartItemsAdapter = getCartItemAdapter();
        lvCartItems.setAdapter(cartItemsAdapter);

        cartSize.setText(String.valueOf(currentOrderManager.cartSize()));
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentOrderManager.checkOut();
            }
        });
    }

    private void initCart() {
        currentOrderManager = ((TravellerApp) getActivity().getApplication()).getCurrentOrderManager();
        if (currentOrderManager.cartSize() > 0) {
            EventBus.getDefault().post(
                    new CurrentOrderManager.CartModifiedEvent(currentOrderManager.cartSize()));
        }
    }

//    private void initView(FragmentOrderBinding rootBinding) {
//        cartItemsAdapter = getCartItemAdapter();
//        rootBinding.lvCartItems.setAdapter(cartItemsAdapter);
//        rootBinding.setOrder(currentOrderManager.getOrder());
//        rootBinding.btnCheckout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentOrderManager.checkOut();
//            }
//        });
//    }

    @NonNull
    private CartItemAdapter getCartItemAdapter() {
        return new CartItemAdapter(
                getActivity(),
                R.layout.item_order_current,
                currentOrderManager.getOrder().getOrderItems());
    }

    public void onEvent(CurrentOrderManager.ModifyCartEvent event) {
        cartItemsAdapter.notifyDataSetChanged();
    }
}

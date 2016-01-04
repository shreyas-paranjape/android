package com.goaamigo.traveller.module.product.view.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goaamigo.traveller.R;

public class OrderStatusFragment extends Fragment {

    public OrderStatusFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_order_status, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {

    }
}

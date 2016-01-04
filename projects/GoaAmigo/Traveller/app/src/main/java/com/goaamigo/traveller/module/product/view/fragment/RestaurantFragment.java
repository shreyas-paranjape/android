package com.goaamigo.traveller.module.product.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.adapter.StringDialog;
import com.view.fragment.AbstractFragment;

import model.catalogue.Product;

public class RestaurantFragment extends AbstractFragment {
    private Product product;

    public RestaurantFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        product = (Product) getArguments().getSerializable("PRODUCT");
        View v = inflater.inflate(R.layout.fragment_restaurant, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        TextView name =(TextView)v.findViewById(R.id.detailFragName);
        TextView detail =(TextView)v.findViewById(R.id.detail);
        detail.setText(product.getProductCategory().getName());
        name.setText(product.getName());
        TextView terms = (TextView) v.findViewById(R.id.tv_terms);
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringDialog term = new StringDialog(R.string.terms);
                term.show(getFragmentManager(), "dialog");
            }
        });
    }
}

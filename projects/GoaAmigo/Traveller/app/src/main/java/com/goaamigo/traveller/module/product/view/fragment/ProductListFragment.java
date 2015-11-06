package com.goaamigo.traveller.module.product.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.adapter.ProductAdapter;

public class ProductListFragment extends Fragment {

    private ProductAdapter productAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productAdapter = (ProductAdapter) getArguments()
                .getSerializable("PRODUCT_ADAPTER");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product_list, container, false);
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rvProducts);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(productAdapter);
        return v;
    }


}

package com.goaamigo.traveller.module.product.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import model.catalogue.Product;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProductListFragment extends Fragment {
    private List<Product> productList;


    public ProductListFragment() {
    }

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

        productList = new ArrayList<>();
        productList.add(new Product("Vivanta by taj","mapusa","4","50%","3500",R.drawable.vivataimage1));
        productList.add(new Product("Hotel Goa International","panajim","5","10%","3530",R.drawable.vivataimage3));
        productList.add(new Product("Hotel Solmar","ponda","3","40%","3550",R.drawable.vivataimage1));
        productList.add(new Product("Goa Marriott Resort & Spa","goa","5","20%","320",R.drawable.vivataimage2));
        productList.add(new Product("Hotel Magsons Center, Cafe Coffee Day","baga","2","20%","320",R.drawable.vivataimage3));
        productList.add(new Product("Hotel Magsons Center, Cafe Coffee Day","vasco","3","20%","320",R.drawable.vivataimage1));
        productList.add(new Product("Food Land","ponda","4","20%","320",R.drawable.vivataimage2));
        productList.add(new Product("Rio Restaurant","mapusa","5","20%","320",R.drawable.vivataimage3));

        View v = inflater.inflate(R.layout.fragment_product_list, container, false);
        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rvProducts);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        productAdapter = new ProductAdapter(productList);
        rv.setAdapter(productAdapter);
        return v;
    }


}

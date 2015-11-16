package com.goaamigo.traveller.module.product.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

import model.catalogue.ProductCategory;

public class FragmentSelectProduct extends Fragment {

    List<ProductCategory> productCategories = new ArrayList<ProductCategory>();

    public FragmentSelectProduct() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_select_product, container, false);
        initView(v);
        return v;
    }
    private void initView(View v) {

        productCategories.add(new ProductCategory("hotel",R.drawable.ic_accessibility_black_24dp));
        productCategories.add(new ProductCategory("activity",R.drawable.ic_accessibility_black_24dp));
        productCategories.add(new ProductCategory("boat ride",R.drawable.ic_accessibility_black_24dp));
        productCategories.add(new ProductCategory("water sport",R.drawable.ic_accessibility_black_24dp));
        productCategories.add(new ProductCategory("stay",R.drawable.ic_accessibility_black_24dp));
        productCategories.add(new ProductCategory("beach",R.drawable.ic_accessibility_black_24dp));

        ArrayAdapter<ProductCategory> adapter = new ProductCategoryListAdapter();
        ListView selectProduct = (ListView) v.findViewById(R.id.lv_select_product);
        selectProduct.setAdapter(adapter);
    }

    private class ProductCategoryListAdapter extends ArrayAdapter<ProductCategory> {

        public ProductCategoryListAdapter() {
            super(getActivity(),R.layout.item_product_select,productCategories);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_product_select,parent,false);
            }
            ProductCategory product = productCategories.get(position);
            ImageView image = (ImageView) getActivity().findViewById(R.id.iv_product_select);
            TextView textView = (TextView) getActivity().findViewById(R.id.tv_product_select);
            image.setImageResource(product.getProductImage());
            textView.setText(product.getName());

            return  convertView;
        }
    }
}

package com.goaamigo.traveller.module.product.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.goaamigo.traveller.R;

import java.util.ArrayList;
import java.util.List;

import model.catalogue.ProductCategory;

public class FragmentSelectProduct extends Fragment {

    List<ProductCategory> productCategories = new ArrayList<>();

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
        productCategories.add(new ProductCategory("activity",R.drawable.ic_help_black_24dp));
        productCategories.add(new ProductCategory("boat ride",R.drawable.ic_home_black_24dp));
        productCategories.add(new ProductCategory("water sport",R.drawable.ic_account_circle_black_24dp));
        productCategories.add(new ProductCategory("stay",R.drawable.ic_sort_black_24dp));
        productCategories.add(new ProductCategory("beach",R.drawable.ic_help_black_24dp));

        ArrayAdapter<ProductCategory> adapter = new ProductCategoryListAdapter(productCategories);
        ListView selectProduct = (ListView) v.findViewById(R.id.lv_select_product);
        selectProduct.setAdapter(adapter);
        selectProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }

    private class ProductCategoryListAdapter extends ArrayAdapter<ProductCategory> {
        TextView textView;
        List<ProductCategory> data;
        public ProductCategoryListAdapter(List<ProductCategory> data) {
            super(getActivity(),R.layout.item_product_select,productCategories);
            this.data = data;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_product_select,parent,false);
            }
            ImageView image = (ImageView) convertView.findViewById(R.id.iv_product_select);
            textView = (TextView) convertView.findViewById(R.id.tv_product_select);
            image.setImageResource(productCategories.get(position).getProductImage());
            textView.setText(productCategories.get(position).getName());

            return  convertView;
        }
    }
}

package com.goaamigo.traveller.module.product.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.adapter.LocationAdapter;
import com.view.fragment.AbstractFragment;

import java.util.ArrayList;
import java.util.List;

import model.catalogue.ProductCategory;

public class RestAmenities extends AbstractFragment {
    List<ProductCategory> list = new ArrayList<>();

    public RestAmenities() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.item_list_view, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        list.add(new ProductCategory("Veg"));
        list.add(new ProductCategory("NonVeg"));
        list.add(new ProductCategory("Delivery"));
        list.add(new ProductCategory("BreakFast"));
        list.add(new ProductCategory("Lunch"));
        list.add(new ProductCategory("Dinner"));
        ListView listView = (ListView) v.findViewById(R.id.lvItems);
        LocationAdapter adapter = new LocationAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            boolean click = true;

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = (TextView) view.findViewById(R.id.tvLocation);
                ImageView imageViewDone = (ImageView) view.findViewById(R.id.ivDone);
                if (click) {
                    textView.setTextColor(Color.BLUE);
                    imageViewDone.setColorFilter(Color.BLUE);
                    click = false;
                } else {
                    textView.setTextColor(Color.WHITE);
                    imageViewDone.setColorFilter(Color.BLACK);
                    click = true;
                }
            }
        });
    }
}

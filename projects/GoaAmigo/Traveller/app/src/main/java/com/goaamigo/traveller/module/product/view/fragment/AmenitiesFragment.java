package com.goaamigo.traveller.module.product.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.adapter.AmenitiesAdapter;
import com.view.fragment.AbstractFragment;

import java.util.ArrayList;
import java.util.List;

import model.catalogue.ProductCategory;

public class AmenitiesFragment extends AbstractFragment {

    List<ProductCategory> listAminities = new ArrayList<>();

    public AmenitiesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_list_view, container, false);
        initView(v);
        return v;
    }

    private void initView(View view) {
        listAminities.add(new ProductCategory("Free WIFI", R.drawable.ic_wifi_black_24dp));
        listAminities.add(new ProductCategory("Bar", R.drawable.ic_wifi_black_24dp));
        listAminities.add(new ProductCategory("Parking", R.drawable.ic_wifi_black_24dp));
        listAminities.add(new ProductCategory("Restaurant", R.drawable.ic_wifi_black_24dp));
        listAminities.add(new ProductCategory("Swimming Pool", R.drawable.ic_wifi_black_24dp));
        ListView listView = (ListView) view.findViewById(R.id.lvItems);
        AmenitiesAdapter adapter = new AmenitiesAdapter(getActivity(), listAminities);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            boolean click = true;

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView imageView = (ImageView) view.findViewById(R.id.ivAmenities);
                ImageView imageViewDone = (ImageView) view.findViewById(R.id.ivDone);

                if (click) {
                    imageView.setColorFilter(Color.BLUE);
                    imageViewDone.setColorFilter(Color.BLUE);
                    click = false;
                } else {
                    imageView.setColorFilter(Color.BLACK);
                    imageViewDone.setColorFilter(Color.BLACK);
                    click = true;
                }
            }
        });
    }
}

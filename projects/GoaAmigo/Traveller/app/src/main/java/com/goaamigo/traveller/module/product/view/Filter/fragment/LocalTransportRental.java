package com.goaamigo.traveller.module.product.view.Filter.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.activity.ProductsActivity;
import com.goaamigo.traveller.module.product.view.adapter.LocationAdapter;
import com.view.fragment.AbstractFragment;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import model.catalogue.ProductCategory;

public class LocalTransportRental extends AbstractFragment {
    private List<ProductCategory> list = new ArrayList<>();

    public LocalTransportRental() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_local_transport_rental, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        list.add(new ProductCategory("panajim"));
        list.add(new ProductCategory("mapusa"));
        list.add(new ProductCategory("margao"));
        list.add(new ProductCategory("ponda"));
        list.add(new ProductCategory("vasco"));
        final TextView location = (TextView) v.findViewById(R.id.tvLocation);
        final ListView listView = (ListView) v.findViewById(R.id.listView);
        LocationAdapter adapter = new LocationAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                location.setText(list.get(i).getName().toString());
                EventBus.getDefault().post(new ProductsActivity.SlidingTabDismissEvent());
            }
        });
    }
}

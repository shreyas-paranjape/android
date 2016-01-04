package com.goaamigo.traveller.module.product.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goaamigo.traveller.R;
import com.view.adapter.list.AbstractListAdapter;

import java.util.ArrayList;
import java.util.List;

import model.catalogue.ProductCategory;

public class LocationAdapter extends
        AbstractListAdapter<ProductCategory> {

    TextView locationName;
    List<ProductCategory> list = new ArrayList<>();

    public LocationAdapter(Context context, List<ProductCategory> objects) {
        super(context, objects);
        this.list = objects;
    }

    @Override
    protected View getView(int position, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_location, parent, false);
        locationName = (TextView) rowView.findViewById(R.id.tvLocation);
        ImageView imageViewDone = (ImageView) rowView.findViewById(R.id.ivDone);
        imageViewDone.setColorFilter(Color.BLACK);
        locationName.setText(list.get(position).getName());
        return rowView;
    }
}

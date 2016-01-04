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

public class AmenitiesAdapter extends
        AbstractListAdapter<ProductCategory> {

    TextView locationName;
    List<ProductCategory> list = new ArrayList<>();

    public AmenitiesAdapter(Context context, List<ProductCategory> objects) {
        super(context, objects);
        this.list = objects;
    }

    @Override
    protected View getView(int position, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_amenities, parent, false);
        ImageView imageAmenities = (ImageView) rowView.findViewById(R.id.ivAmenities);
        locationName = (TextView) rowView.findViewById(R.id.tvAmenities);
        ImageView imageViewDone = (ImageView) rowView.findViewById(R.id.ivDone);
        imageViewDone.setColorFilter(Color.BLACK);
//        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.cb_location);
        imageAmenities.setImageResource(list.get(position).getProductImage());
        locationName.setText(list.get(position).getName());
        return rowView;
    }
}

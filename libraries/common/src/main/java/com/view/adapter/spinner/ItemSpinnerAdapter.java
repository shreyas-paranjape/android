package com.view.adapter.spinner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.R;
import com.view.model.Item;

import java.util.List;

public class ItemSpinnerAdapter extends AbstractSpinnerAdapter<Item> {

    public ItemSpinnerAdapter(Context context, List<Item> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View collapsedView;
        Item item = getItem(position);
        if (convertView == null) {
            collapsedView = mInflater.inflate(R.layout.layout_imageview, parent, false);
        } else {
            collapsedView = convertView;
        }
        ImageView linkImage = (ImageView) collapsedView.findViewById(R.id.ivIcon);
        linkImage.setBackgroundResource(getItem(position).getImageId());
        return collapsedView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View collapsedView;
        Item item = getItem(position);
        if (convertView == null) {
            collapsedView = mInflater.inflate(R.layout.layout_image_text, parent, false);
        } else {
            collapsedView = convertView;
        }
        TextView textView = (TextView) collapsedView.findViewById(R.id.tvLabel);
        textView.setText(item.getName());
        ImageView linkImage = (ImageView) collapsedView.findViewById(R.id.ivIcon);
        linkImage.setBackgroundResource(getItem(position).getImageId());
        return collapsedView;
    }
}

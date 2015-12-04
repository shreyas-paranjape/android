package com.admin.module.app.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.view.adapter.list.AbstractListAdapter;
import com.view.model.Item;

import java.util.List;

import com.admin.R;


public class DrawerAdapter extends AbstractListAdapter<Item> {

    public DrawerAdapter(Context context, List<Item> items) {
        super(context, items);
    }

    @Override
    protected View getView(int position, ViewGroup parent) {
        View v = inflater.inflate(R.layout.item_drawer_link, parent, false);
        TextView txtListChild = (TextView) v.findViewById(R.id.tv_drawer_link);
        txtListChild.setText(getItem(position).getName());
        ImageView linkImage = (ImageView) v.findViewById(R.id.iv_drawer_link);
        linkImage.setBackgroundResource(getItem(position).getImageId());
        return v;
    }
}

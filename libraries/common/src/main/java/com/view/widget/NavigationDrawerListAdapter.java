package com.view.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.view.model.Item;

import java.util.List;

public abstract class NavigationDrawerListAdapter extends ArrayAdapter<Item> {

    protected final LayoutInflater inflater;

    public NavigationDrawerListAdapter(Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(getItemId(), parent, false);
        }
        initView(convertView, position);
        return convertView;
    }

    protected abstract int getItemId();

    protected abstract void initView(View convertView, int position);
}

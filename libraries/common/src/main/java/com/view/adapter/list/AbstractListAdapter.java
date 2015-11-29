package com.view.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

@SuppressWarnings("unchecked")
public abstract class AbstractListAdapter<T> extends ArrayAdapter<T> {

    protected final LayoutInflater inflater;

    public AbstractListAdapter(Context context, List<T> objects) {
        super(context, 0, objects);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View toReturn;
        if (convertView == null) {
            toReturn = getView(position, parent);
        } else {
            toReturn = convertView;
        }
        return toReturn;
    }

    protected abstract View getView(final int position, ViewGroup parent);


}

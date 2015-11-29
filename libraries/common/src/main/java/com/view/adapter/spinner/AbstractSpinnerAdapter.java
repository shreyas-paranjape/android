package com.view.adapter.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.List;

public abstract class AbstractSpinnerAdapter<T> extends ArrayAdapter<T> {

    protected final int resource;
    protected final LayoutInflater mInflater;

    public AbstractSpinnerAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
        this.resource = resource;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

}

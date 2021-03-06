package com.view.adapter.spinner;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.view.adapter.spinner.AbstractSpinnerAdapter;

import java.util.List;

public abstract class FormSpinnerAdapter<T> extends AbstractSpinnerAdapter<T> {

    public FormSpinnerAdapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        View view;
        TextView text;

        if (convertView == null) {
            view = mInflater.inflate(resource, parent, false);
        } else {
            view = convertView;
        }

        try {
            text = (TextView) view;
        } catch (ClassCastException e) {
            Log.e("ArrayAdapter", "You must supply a resource ID for a TextView");
            throw new IllegalStateException(
                    "ArrayAdapter requires the resource ID to be a TextView", e);
        }
        text.setText(getText(getItem(position)));
        return view;
    }

    protected abstract String getText(T item);
}

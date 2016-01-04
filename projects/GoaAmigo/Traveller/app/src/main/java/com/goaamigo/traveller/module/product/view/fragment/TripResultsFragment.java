package com.goaamigo.traveller.module.product.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goaamigo.traveller.R;
import com.view.fragment.AbstractFragment;

public class TripResultsFragment extends AbstractFragment {

    public TripResultsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_trip_results, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {

    }
}

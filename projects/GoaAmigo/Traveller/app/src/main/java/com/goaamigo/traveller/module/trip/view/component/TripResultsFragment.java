package com.goaamigo.traveller.module.trip.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goaamigo.traveller.R;

public class TripResultsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Trip results");
        View v = inflater.inflate(R.layout.fragment_trip_result, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {

    }
}

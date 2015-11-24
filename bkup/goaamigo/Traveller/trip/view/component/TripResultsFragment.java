package com.goaamigo.traveller.module.trip.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import model.trip.Trip;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.trip.view.adapter.TripRVAdapter;

import java.util.ArrayList;
import java.util.List;

public class TripResultsFragment extends Fragment {

    List<Trip> list;
    TripRVAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Trip results");
        View v = inflater.inflate(R.layout.fragment_trip_result, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {

        list = new ArrayList<>();
        list.add(new Trip(R.drawable.image4,"trip 1","this is trip 1"));
        list.add(new Trip(R.drawable.image4,"trip 1","this is trip 1"));
        list.add(new Trip(R.drawable.image4,"trip 1","this is trip 1"));

        RecyclerView rv = (RecyclerView) v.findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        adapter = new TripRVAdapter(list, getActivity());
        rv.setAdapter(adapter);

    }
}

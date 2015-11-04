package com.goaamigo.traveller.module.trip.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goaamigo.model.trip.Day;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.singleton.Constant;
import com.goaamigo.traveller.module.trip.view.adapter.EventAdapter;

public class DayFragment extends Fragment {

    private Day day;

    public DayFragment(Day day) {
        this.day = day;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        day = (Day) args.getSerializable(Constant.DAY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_day, container, false);
        initListView(rootView);
        return rootView;
    }

    private void initListView(View rootView) {
        RecyclerView eventsRecycler = (RecyclerView) rootView.findViewById(R.id.rv_day_events);
        eventsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        eventsRecycler.setAdapter(new EventAdapter(getActivity(), day.getEvents()));
    }
}

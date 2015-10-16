package com.goaamigo.traveller.module.trip.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.goaamigo.traveller.R;

import de.greenrobot.event.EventBus;

public class SearchTripFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Search trips");
        View v = inflater.inflate(R.layout.fragment_search_trip, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        Button btnFindTrip = (Button) v.findViewById(R.id.btnFindTrip);
        btnFindTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FindTripButtonClickEvent());
            }
        });
    }

    public static class FindTripButtonClickEvent {

    }

}

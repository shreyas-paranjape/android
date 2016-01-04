package com.goaamigo.traveller.module.product.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.event.ChangeContentEvent;
import com.goaamigo.traveller.R;
import com.view.fragment.AbstractFragment;

import de.greenrobot.event.EventBus;

public class TripFilter extends AbstractFragment {

    public TripFilter() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_trip_filter, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        Button findTrip = (Button) v.findViewById(R.id.btnFindTrip);
        findTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new ChangeContentEvent(TripResultsFragment.class));

            }
        });
    }
}

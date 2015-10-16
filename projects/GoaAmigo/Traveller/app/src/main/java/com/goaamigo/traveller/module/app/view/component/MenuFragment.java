package com.goaamigo.traveller.module.app.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.deal.view.component.FindDealsFragment;
import com.goaamigo.traveller.module.trip.view.component.SearchTripFragment;

import de.greenrobot.event.EventBus;

public class MenuFragment extends Fragment {

    private static final String TAG = MenuFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        Button btnSearchTrip = (Button) v.findViewById(R.id.btnSearchTrip);
        btnSearchTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "in button click");
                EventBus.getDefault().post(new SearchTripButtonClickedEvent());
            }
        });

        Button btnFindDeals = (Button) v.findViewById(R.id.btnFindDeals);
        btnFindDeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "in button click");
                EventBus.getDefault().post(new FindDealsButtonClickedEvent());
            }
        });

    }

    public static class SearchTripButtonClickedEvent implements HomeActivity.MenuButtonEvent {
        @Override
        public Fragment getFragment() {
            return new SearchTripFragment();
        }
    }

    public static class FindDealsButtonClickedEvent implements HomeActivity.MenuButtonEvent {
        @Override
        public Fragment getFragment() {
            return new FindDealsFragment();
        }
    }

}

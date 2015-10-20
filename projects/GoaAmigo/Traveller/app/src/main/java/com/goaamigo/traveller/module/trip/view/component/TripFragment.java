package com.goaamigo.traveller.module.trip.view.component;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goaamigo.traveller.module.trip.view.adapter.DayAdapter;
import com.view.widget.SlidingTabLayout;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.trip.view.adapter.EventAdapter;

public class TripFragment extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_trip, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        ViewPager mPager = initPager(rootView);
        initTabs(rootView, mPager);
    }

    private ViewPager initPager(View rootView) {
        PagerAdapter mPagerAdapter = new DayAdapter(getActivity().getFragmentManager());
        ViewPager mPager = (ViewPager) rootView.findViewById(R.id.pager);
        mPager.setAdapter(mPagerAdapter);
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                getActivity().invalidateOptionsMenu();
            }
        });
        return mPager;
    }

    private void initTabs(View rootView, ViewPager mPager) {
        SlidingTabLayout slidingTabLayout =
                (SlidingTabLayout) rootView.findViewById(R.id.tabs);
        slidingTabLayout.setViewPager(mPager);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.indicator_color);
            }

            @Override
            public int getDividerColor(int position) {
                return getResources().getColor(R.color.primary_color);
            }
        });
    }
}

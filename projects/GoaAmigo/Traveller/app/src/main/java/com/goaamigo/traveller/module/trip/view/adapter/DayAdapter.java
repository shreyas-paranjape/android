package com.goaamigo.traveller.module.trip.view.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.goaamigo.model.trip.Day;
import com.goaamigo.traveller.module.app.singleton.Constant;
import com.goaamigo.traveller.module.trip.view.component.DayFragment;

import java.util.ArrayList;
import java.util.List;


public class DayAdapter extends FragmentStatePagerAdapter {
    private List<Day> data;

    public DayAdapter(FragmentManager fm) {
        super(fm);
        this.data = new ArrayList<>();
        data.add(new Day("Day 1"));
        data.add(new Day("Day 2"));
    }

    @Override
    public Fragment getItem(int position) {
        return getDayFragment(position);
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return data != null ? data.get(position).getName() : "";
    }

    private Fragment getDayFragment(int position) {
        Fragment dayFragment = new DayFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constant.DAY, data.get(position));
        return dayFragment;
    }
}
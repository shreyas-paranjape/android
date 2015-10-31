package com.goaamigo.traveller.module.trip.view.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.goaamigo.model.trip.Day;
import com.goaamigo.traveller.module.app.singleton.Constant;
import com.goaamigo.traveller.module.trip.view.component.DayFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class DayAdapter extends FragmentStatePagerAdapter {
    private List<Day> data;
    Date date = new Date();
    String[] monthName = new String[]{"Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"};
    Calendar rightNow = Calendar.getInstance();
    GregorianCalendar cal = new GregorianCalendar();
    public DayAdapter(FragmentManager fm) {
        super(fm);
        rightNow.set(date.getYear(), date.getMonth(), date.getDay());
        this.data = new ArrayList<>();
        data.add(new Day(date.getDate() + " " + monthName[date.getMonth()]));
        data.add(new Day( date.getDate() + " " + monthName[date.getMonth()]));
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
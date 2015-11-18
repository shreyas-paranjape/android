package com.goaamigo.traveller.module.trip.view.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

import model.trip.Day;
import model.trip.Event;
import com.goaamigo.traveller.R;
import com.util.Constant;
import com.goaamigo.traveller.module.trip.view.component.DayFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class DayAdapter extends FragmentStatePagerAdapter {
    private List<Day> data;
    Date date = new Date();
    List<Event> obj1;
    List<Event> obj2;
    List<Event> obj3;
    String[] monthName = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
    Calendar rightNow = Calendar.getInstance();
    GregorianCalendar cal = new GregorianCalendar();

    public DayAdapter(FragmentManager fm) {
        super(fm);
        rightNow.set(date.getYear(), date.getMonth(), date.getDay());
        this.data = new ArrayList<>();


        obj1 = new ArrayList<>();
        obj1.add(new Event(R.drawable.image4, "tab1 event 1", "description 1"));
        obj1.add(new Event(R.drawable.image4, "tab1 event 2", "description 2"));
        obj1.add(new Event(R.drawable.image4, "tab1 event 3", "description 3"));
        obj1.add(new Event(R.drawable.image4, "tab1 event 1", "description 1"));
        obj1.add(new Event(R.drawable.image4, "tab1 event 2", "description 2"));
        obj1.add(new Event(R.drawable.image4, "tab1 event 3", "description 3"));

        obj2 = new ArrayList<>();
        obj2.add(new Event(R.drawable.image4, "tab2 event 1", "description 1"));
        obj2.add(new Event(R.drawable.image4, "tab2 event 2", "description 2"));
        obj2.add(new Event(R.drawable.image4, "tab2 event 3", "description 3"));
        obj2.add(new Event(R.drawable.image4, "tab2 event 1", "description 1"));
        obj2.add(new Event(R.drawable.image4, "tab2 event 2", "description 2"));
        obj2.add(new Event(R.drawable.image4, "tab2 event 3", "description 3"));

        obj3 = new ArrayList<>();
        obj3.add(new Event(R.drawable.image4, "tab3 event 1", "description 1"));
        obj3.add(new Event(R.drawable.image4, "tab3 event 2", "description 2"));
        obj3.add(new Event(R.drawable.image4, "tab3 event 3", "description 3"));
        obj3.add(new Event(R.drawable.image4, "tab3 event 1", "description 1"));
        obj3.add(new Event(R.drawable.image4, "tab3 event 2", "description 2"));
        obj3.add(new Event(R.drawable.image4, "tab3 event 3", "description 3"));

        data.add(new Day(obj1, "day1"));
        data.add(new Day(obj2, "day1"));
        data.add(new Day(obj3, "day1"));
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
        Fragment dayFragment = new DayFragment(data.get(position));
        Bundle args = new Bundle();
        args.putSerializable(Constant.DAY, this.data.get(position));
        return dayFragment;
    }
}
package com.goaamigo.traveller.module.product.view.adapter;

import android.os.Bundle;
import android.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TabHost;

import com.goaamigo.traveller.R;

import java.util.Calendar;
import java.util.Date;

public class DateRangePickerFragment extends DialogFragment implements View.OnClickListener {

    private OnDateRangeSelectedListener onDateRangeSelectedListener;

    private TabHost tabHost;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private Button butSetDateRange;
    boolean is24HourMode;

    public DateRangePickerFragment() {
        // Required empty public constructor
    }

    public static DateRangePickerFragment newInstance(OnDateRangeSelectedListener callback, boolean is24HourMode) {
        DateRangePickerFragment dateRangePickerFragment = new DateRangePickerFragment();
        dateRangePickerFragment.initialize(callback, is24HourMode);
        return dateRangePickerFragment;
    }

    public void initialize(OnDateRangeSelectedListener callback,
                           boolean is24HourMode) {
        onDateRangeSelectedListener = callback;
        this.is24HourMode = is24HourMode;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.date_range_picker, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        tabHost = (TabHost) root.findViewById(R.id.tabHost);
        butSetDateRange = (Button) root.findViewById(R.id.but_set_time_range);
        startDatePicker = (DatePicker) root.findViewById(R.id.start_date_picker);
        endDatePicker = (DatePicker) root.findViewById(R.id.end_date_picker);
        startDatePicker.setMinDate(getDate(0).getTime());
        endDatePicker.setMinDate(getDate(1).getTime());
        butSetDateRange.setOnClickListener(this);
        tabHost.findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec startDatePage = tabHost.newTabSpec("start");
        startDatePage.setContent(R.id.start_date_group);
        startDatePage.setIndicator(getString(R.string.title_tab_start_date));

        TabHost.TabSpec endDatePage = tabHost.newTabSpec("end");
        endDatePage.setContent(R.id.end_date_group);
        endDatePage.setIndicator(getString(R.string.ttile_tab_end_date));

        tabHost.addTab(startDatePage);
        tabHost.addTab(endDatePage);
        startDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabHost.setCurrentTab(1);
            }
        });
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() == null)
            return;
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    public void setOnDateRangeSelectedListener(OnDateRangeSelectedListener callback) {
        this.onDateRangeSelectedListener = callback;
    }

    @Override
    public void onClick(View v) {
        if (v == startDatePicker) {
            tabHost.setCurrentTab(1);
        }
        dismiss();
        onDateRangeSelectedListener.onDateRangeSelected(getDateFromDatePicker(startDatePicker).getDay(), startDatePicker.getDayOfMonth(), startDatePicker.getMonth(),
                endDatePicker.getDayOfMonth(), endDatePicker.getMonth(), getDateFromDatePicker(endDatePicker).getDay());
        endDatePicker.setMinDate(getDateFromDatePicker(startDatePicker).getTime());

    }

    public interface OnDateRangeSelectedListener {
        void onDateRangeSelected(int startDate, int startDay, int startMonth, int endDay, int endMonth, int endDate);
    }

    public static java.util.Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    public java.util.Date getDate(int roll) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, roll);
        return calendar.getTime();
    }
}

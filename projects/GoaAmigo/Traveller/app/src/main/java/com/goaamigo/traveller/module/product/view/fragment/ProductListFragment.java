package com.goaamigo.traveller.module.product.view.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.adapter.DateRangePickerFragment;
import com.goaamigo.traveller.module.product.view.adapter.ProductAdapter;
import com.view.adapter.recycler.AbstractRecyclerAdapter;
import com.view.fragment.AbstractRecyclerFragment;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Date;

import de.greenrobot.event.EventBus;
import model.catalogue.Product;
import repository.catalogue.ProductRepo;

public class ProductListFragment
        extends AbstractRecyclerFragment<Product>
        implements DateRangePickerFragment.OnDateRangeSelectedListener {
    private TextView tvCheckInDate, tvCheckOutDate, tvCheckInDay, tvCheckOutDay, tvCheckInMonth, tvCheckOutMonth;
    private TextView tvActivityClock, tvActivityPlus, tvActivityCount, tvActivityMinus;
    private String[] monthName = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
    private String[] dayName = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    LinearLayout layoutClock;
    private TextView tvFrom, tvTo, tvRoom, tvPeople;
    int count = 1;
    DatePickerDialog activityDatePicker;
    LayoutInflater layoutInflater;
    LinearLayout linearLayout, layoutStayShrink, llBtn, layoutActivities;
    int adult = 0, child = 0;
    int countA, countC, countActivity;
    Button apply, activityApply;
    private Calendar cal;
    private int day, month, year;
    protected String temp = "Stay";

    public ProductListFragment() {
    }

    private void initDate(View v) {
        tvActivityClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        final LinearLayout checkIn = (LinearLayout) v.findViewById(R.id.layoutCheckIn);
        LinearLayout checkOut = (LinearLayout) v.findViewById(R.id.layoutCheckOut);
        tvCheckInDate = (TextView) v.findViewById(R.id.CheckInDate);
        tvCheckOutDate = (TextView) v.findViewById(R.id.CheckOutDate);
        tvCheckInDay = (TextView) v.findViewById(R.id.CheckInDay);
        tvCheckOutDay = (TextView) v.findViewById(R.id.CheckOutDay);
        tvCheckInMonth = (TextView) v.findViewById(R.id.CheckInMonth);
        tvCheckOutMonth = (TextView) v.findViewById(R.id.CheckOutMonth);
        Calendar calendar = Calendar.getInstance();
        final Date today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        final Date tomorrow = calendar.getTime();

        tvCheckInDate.setText(String.valueOf(today.getDate()));
        tvCheckOutDate.setText(String.valueOf(tomorrow.getDate()));
        tvCheckInDay.setText(String.valueOf(dayName[today.getDay()]));
        tvCheckOutDay.setText(String.valueOf(dayName[tomorrow.getDay()]));
        tvCheckInMonth.setText(String.valueOf(monthName[today.getMonth()]));
        tvCheckOutMonth.setText(String.valueOf(monthName[tomorrow.getMonth()]));
        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateRangePickerFragment dateRangePickerFragment = DateRangePickerFragment.newInstance(ProductListFragment.this, false);
                dateRangePickerFragment.show(getFragmentManager(), "datePicker");
            }
        });
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateRangePickerFragment dateRangePickerFragment = DateRangePickerFragment.newInstance(ProductListFragment.this, false);
                dateRangePickerFragment.show(getFragmentManager(), "datePicker");
            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFrom.setText(monthName[today.getMonth()] + " " + today.getDate());
                tvTo.setText(tomorrow.getDate() + " " + monthName[tomorrow.getMonth()]);
                tvRoom.setText("2");
                tvPeople.setText("5");
                layoutStayShrink.setVisibility(View.VISIBLE);
                layoutClock.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                llBtn.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDateRangeSelected(int startDate, int startDay, int startMonth, int endDay, int endMonth, int endDate) {
        tvCheckInDate.setText(String.valueOf(startDay));
        tvCheckOutDate.setText(String.valueOf(endDay));
        tvCheckInDay.setText(String.valueOf(dayName[startDate]));
        tvCheckOutDay.setText(String.valueOf(dayName[endDate]));
        tvCheckInMonth.setText(String.valueOf(monthName[startMonth]));
        tvCheckOutMonth.setText(String.valueOf(monthName[endMonth]));
    }


    @Override
    protected void initView(View v) {
        layoutInflater = (LayoutInflater) getActivity().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        layoutClock = (LinearLayout) v.findViewById(R.id.lvDatePicker);
        linearLayout = (LinearLayout) v.findViewById(R.id.ll);
        Button addRoom = (Button) v.findViewById(R.id.addRoom);
        tvFrom = (TextView) v.findViewById(R.id.tvFrom);
        tvTo = (TextView) v.findViewById(R.id.tvTo);
        tvRoom = (TextView) v.findViewById(R.id.tvRooms);
        tvPeople = (TextView) v.findViewById(R.id.tvPeople);

        llBtn = (LinearLayout) v.findViewById(R.id.llBtn);
        apply = (Button) v.findViewById(R.id.apply);
        layoutStayShrink = (LinearLayout) v.findViewById(R.id.lvStayShrink);

        layoutActivities = (LinearLayout) v.findViewById(R.id.llActivity);

        tvActivityClock = (TextView) v.findViewById(R.id.tvActivitiesDate);
        tvActivityPlus = (TextView) v.findViewById(R.id.tvActivitiesPlus);
        tvActivityCount = (TextView) v.findViewById(R.id.tvActivitiesCount);
        tvActivityMinus = (TextView) v.findViewById(R.id.tvActivitiesMinus);
        activityApply = (Button) v.findViewById(R.id.btnActivityApply);

        addRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout.addView(addCustomView(linearLayout, count));
            }
        });
        initDate(v);
        countActivity = 0;
//        tvActivityClock.setText("");

        tvActivityMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countActivity--;
            }
        });
        tvActivityPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countActivity++;
            }
        });
        tvActivityCount.setText("" + countActivity);
        activityApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public View addCustomView(LinearLayout linearLayout, int count) {
        countA = 0;
        countC = 0;
        View v = layoutInflater.inflate(R.layout.item_people, linearLayout, false);
        TextView cm, cc, cp, am, ac, ap, room;
        room = (TextView) v.findViewById(R.id.room);
        am = (TextView) v.findViewById(R.id.adultMinus);
        ac = (TextView) v.findViewById(R.id.adultCount);
        ap = (TextView) v.findViewById(R.id.adultPlus);
        cm = (TextView) v.findViewById(R.id.chilsMinus);
        cc = (TextView) v.findViewById(R.id.childCount);
        cp = (TextView) v.findViewById(R.id.childPlus);
        return v;
    }

    public static class MessageEvent {
        public final String message;

        public MessageEvent(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public void onEvent(ProductListFragment.MessageEvent event) {
        temp = event.getMessage();
        if (event.getMessage().equals("Stay")) {
            layoutActivities.setVisibility(View.GONE);
            layoutStayShrink.setVisibility(View.GONE);
            layoutClock.setVisibility(View.VISIBLE);
            llBtn.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
        } else if (event.getMessage().equals("Restaurant") || event.getMessage().equals("LocalTransport_taxi") || event.getMessage().equals("LocalTransport_rental")) {
            layoutActivities.setVisibility(View.GONE);
            layoutStayShrink.setVisibility(View.GONE);
            layoutClock.setVisibility(View.GONE);
            llBtn.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
        } else if (event.getMessage().equals("Activities")) {
            layoutActivities.setVisibility(View.VISIBLE);
            layoutStayShrink.setVisibility(View.GONE);
            layoutClock.setVisibility(View.GONE);
            llBtn.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected Class getFilterFragmentClass() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_list;
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_blank;
    }

    @Override
    protected int getRecyclerId() {
        return R.id.rvProducts;
    }

    @Override
    protected AbstractRecyclerAdapter getAdapter() {
        return new ProductAdapter(getActivity(), ProductRepo.getAll());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}

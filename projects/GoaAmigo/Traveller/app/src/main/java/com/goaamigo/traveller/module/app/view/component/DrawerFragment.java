package com.goaamigo.traveller.module.app.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.view.adapter.DrawerAdapter;
import com.view.fragment.ListNavigationDrawer;
import com.view.model.Item;
import com.view.widget.NavigationDrawerListAdapter;
import com.wdullaer.materialdatetimepicker.time.CircleView;

public class DrawerFragment extends ListNavigationDrawer {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        drawerItems.add(new Item("Home", R.drawable.ic_home_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new MenuFragment();
            }
        });
        drawerItems.add(new Item("Feedback", R.drawable.ic_feedback_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new FeedbackFragment();
            }
        });
        drawerItems.add(new Item("Help", R.drawable.ic_help_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new HelpFragment();
            }
        });
        drawerItems.add(new Item("Contact Us", R.drawable.ic_contact_phone_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new ContactUsFragment();
            }
        });
    }

    @Override
    protected int getListViewId() {
        return R.id.elv_drawer;
    }

    @Override
    protected NavigationDrawerListAdapter getAdapter() {
        return new DrawerAdapter(getActivity(), R.layout.item_drawer_link, drawerItems);
    }

    @Override
    protected void initView(View v) {
        super.initView(v);
        CircleView circleView;
        TextView fk;
        Log.i("aya circle view", "bachao");
        circleView=(CircleView) v.findViewById(R.id.profile_image);
        fk=(TextView) v.findViewById(R.id.tao);
        fk.setText("Jaguar");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_drawer;
    }
}

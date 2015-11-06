package com.goaamigo.traveller.module.app.view.component;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goaamigo.model.trip.trip.Profile;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.view.adapter.DrawerAdapter;
import com.orm.SugarRecord;
import com.view.fragment.ListNavigationDrawer;
import com.view.model.Item;
import com.view.widget.NavigationDrawerListAdapter;

import java.util.List;

import de.greenrobot.event.EventBus;
import de.hdodenhof.circleimageview.CircleImageView;

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
    protected int getLayoutId() {
        return R.layout.fragment_navigation_drawer;
    }

    @Override
    protected void initView(View v) {
        super.initView(v);
        CircleImageView profileImage = (CircleImageView)v.findViewById(R.id.profileImage);
        LinearLayout profile = (LinearLayout)v.findViewById(R.id.layoutProfile);
        TextView name = (TextView)v.findViewById(R.id.tvName);
        TextView email = (TextView)v.findViewById(R.id.tvEmail);
        List<Profile> profiles = SugarRecord.listAll(Profile.class);
        profileImage.setImageResource(R.drawable.heisenberg);
        name.setText("heisenberg");
        email.setText("heisenberg@example.com");


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerItemClicked(
                        new Item() {
                            @Override
                            public Fragment getDisplayFragment() {
                                return new ProfileFragment();
                            }
                        });
            }
        });
    }
}

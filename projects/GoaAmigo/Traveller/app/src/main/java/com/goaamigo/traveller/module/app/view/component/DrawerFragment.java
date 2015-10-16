package com.goaamigo.traveller.module.app.view.component;

import android.app.Fragment;
import android.os.Bundle;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.view.adapter.DrawerAdapter;
import com.view.fragment.ListNavigationDrawer;
import com.view.model.Item;
import com.view.widget.NavigationDrawerListAdapter;

public class DrawerFragment extends ListNavigationDrawer {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawerItems.add(new Item("Feedback", R.drawable.ic_feedback_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new FeedbackFragment();
            }
        });
        drawerItems.add(new Item("Help", R.drawable.ic_help_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        drawerItems.add(new Item("test", R.drawable.ic_accessibility_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
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
}

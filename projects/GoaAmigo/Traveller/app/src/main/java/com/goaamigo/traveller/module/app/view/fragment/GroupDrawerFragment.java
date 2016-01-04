package com.goaamigo.traveller.module.app.view.fragment;

import android.app.Fragment;
import android.os.Bundle;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.view.adapter.GroupDrawerAdapter;
import com.view.adapter.elist.NavigationDrawerAdapter;
import com.view.fragment.ExpandableListNavigationDrawer;
import com.view.model.Item;
import com.view.model.ItemGroup;


public class GroupDrawerFragment extends ExpandableListNavigationDrawer {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDrawer();
    }

    @Override
    protected int getListViewId() {
        return R.id.elv_drawer;
    }

    @Override
    public NavigationDrawerAdapter getAdapter() {
        return new GroupDrawerAdapter(getActivity(), drawerItemGroups);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_drawer;
    }

    private void initDrawer() {
        ItemGroup book = new ItemGroup("Book");
        ItemGroup feedback = new ItemGroup("Feedback");
        ItemGroup account = new ItemGroup("Account");

        book.getItems().add(new Item("Contact Us", R.drawable.ic_notifications_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new ContactUsFragment();
            }
        });
        book.getItems().add(new Item("Stay", R.drawable.ic_notifications_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new StayFragment();
            }
        });
        book.getItems().add(new Item("WishList", R.drawable.ic_notifications_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        book.getItems().add(new Item("Activity", R.drawable.ic_notifications_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new ActivityFragment();
            }
        });
        book.getItems().add(new Item("Trip", R.drawable.ic_notifications_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        feedback.getItems().add(new Item("Menu", R.drawable.ic_notifications_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new ContactUsFragment();
            }
        });
        feedback.getItems().add(new Item("Comment/Review", R.drawable.ic_notifications_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new Commentfragment();
            }
        });
        account.getItems().add(new Item("Menu", R.drawable.ic_notifications_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new ProfileFragment();
            }
        });

        drawerItemGroups.add(book);
        drawerItemGroups.add(account);
        drawerItemGroups.add(feedback);

    }
}

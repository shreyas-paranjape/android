package com.goaamigo.traveller.module.app.view.component;

import android.app.Fragment;
import android.os.Bundle;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.view.adapter.GroupDrawerAdapter;
import com.view.fragment.ExpandableListNavigationDrawer;
import com.view.model.Item;
import com.view.model.ItemGroup;
import com.view.widget.NavigationDrawerAdapter;


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
        ItemGroup account = new ItemGroup("account");

        book.getItems().add(new Item("Menu", R.drawable.ic_restaurant_menu_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new ContactUsFragment();
            }
        });

        feedback.getItems().add(new Item("Menu", R.drawable.ic_restaurant_menu_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new ContactUsFragment();
            }
        });

        account.getItems().add(new Item("Menu", R.drawable.ic_restaurant_menu_black_24dp) {
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

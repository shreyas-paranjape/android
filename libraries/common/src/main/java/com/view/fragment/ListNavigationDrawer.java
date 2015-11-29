package com.view.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.view.adapter.list.AbstractListAdapter;
import com.view.model.Item;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public abstract class ListNavigationDrawer extends NavigationDrawer {

    protected static final List<Item> drawerItems = new ArrayList<>();

    @Override
    protected void initView(View v) {
        ListView mDrawerListView = (ListView) v.findViewById(getListViewId());
        mDrawerListView.setAdapter(getAdapter());
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerItemClicked(drawerItems.get(position));
            }
        });
    }

    protected abstract int getListViewId();

    protected abstract AbstractListAdapter<Item> getAdapter();
}

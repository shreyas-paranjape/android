package com.view.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.view.model.Item;
import com.view.model.ItemGroup;
import com.view.widget.NavigationDrawerAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class ExpandableListNavigationDrawer extends NavigationDrawer {

    protected List<ItemGroup> drawerItemGroups = new ArrayList<>();

    @Override
    protected void initView(View v) {
        ExpandableListView mDrawerListView = (ExpandableListView) v.findViewById(getListViewId());
        expandAllGroups(mDrawerListView);
        disableGroupClick(mDrawerListView);
        mDrawerListView.setGroupIndicator(null);
        mDrawerListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                drawerItemClicked(getItem((int) id));
                return true;
            }
        });
    }

    private Item getItem(int id) {
        int childId = id % 100;
        int groupId = id / 100;
        return drawerItemGroups.get(groupId).getItems().get(childId);
    }

    private void disableGroupClick(ExpandableListView mDrawerListView) {
        mDrawerListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });
    }

    private void expandAllGroups(ExpandableListView mDrawerListView) {
        mDrawerListView.setAdapter(getAdapter());
        for (int i = 0; i < mDrawerListView.getExpandableListAdapter().getGroupCount(); i++) {
            mDrawerListView.expandGroup(i);
        }
    }

    protected abstract int getListViewId();

    public abstract NavigationDrawerAdapter getAdapter();

}


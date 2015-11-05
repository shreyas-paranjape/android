package com.view.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.view.model.ItemGroup;

import java.util.List;

public abstract class NavigationDrawerAdapter extends BaseExpandableListAdapter {

    private final List<ItemGroup> itemGroups;
    protected final LayoutInflater inflater;

    public NavigationDrawerAdapter(Context context, List<ItemGroup> itemGroups) {
        this.itemGroups = itemGroups;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(getGroupLayoutId(), parent, false);
        }
        initGroupView(convertView, groupPosition);
        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(getChildLayoutId(), parent, false);
        }
        initChildView(convertView, groupPosition, childPosition, isLastChild);
        return convertView;
    }

    @Override
    public int getGroupCount() {
        return itemGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemGroups.get(groupPosition).getItems().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return itemGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return itemGroups.get(groupPosition).getItems().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        int offset = groupPosition * 100;
        return childPosition + offset;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    protected abstract void initGroupView(View convertView, int groupPosition);

    protected abstract int getGroupLayoutId();

    protected abstract void initChildView(View convertView, int groupPosition, int childPosition, boolean isLastChild);

    protected abstract int getChildLayoutId();
}

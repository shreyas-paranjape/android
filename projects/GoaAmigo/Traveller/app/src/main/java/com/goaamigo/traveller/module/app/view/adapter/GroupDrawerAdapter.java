package com.goaamigo.traveller.module.app.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.goaamigo.traveller.R;
import com.view.model.Item;
import com.view.model.ItemGroup;
import com.view.widget.NavigationDrawerAdapter;

import java.util.List;


public class GroupDrawerAdapter extends NavigationDrawerAdapter {

    public GroupDrawerAdapter(Context context, List<ItemGroup> itemGroups) {
        super(context, itemGroups);
    }

    @Override
    protected void initGroupView(View convertView, int groupPosition) {
        ItemGroup itemGroup = (ItemGroup) getGroup(groupPosition);
        TextView txtListChild = (TextView) convertView.findViewById(R.id.tv_drawer_link);
        txtListChild.setText(itemGroup.getName());
    }

    @Override
    protected int getGroupLayoutId() {
        return R.layout.item_drawer_group;
    }

    @Override
    protected void initChildView(View convertView, int groupPosition,
                                 int childPosition, boolean isLastChild) {
        Item item = (Item) getChild(groupPosition, childPosition);
        TextView txtListChild = (TextView) convertView.findViewById(R.id.tv_drawer_link);
        txtListChild.setText(item.getName());
        ImageView linkImage = (ImageView) convertView.findViewById(R.id.iv_drawer_link);
        linkImage.setBackgroundResource(item.getImageId());
    }

    @Override
    protected int getChildLayoutId() {
        return R.layout.item_drawer_link;
    }
}

package com.admin.module.order.view.component;

import android.view.MenuItem;
import android.view.View;

import com.admin.module.order.view.adapter.OrderListAdapter;
import com.view.adapter.recycler.AbstractRecyclerAdapter;
import com.view.fragment.AbstractRecyclerFragment;

import java.util.ArrayList;

import com.admin.R;
import model.order.Order;

public class OrderListFragment extends AbstractRecyclerFragment<Order> {

    @Override
    protected int getLayoutId() {
        return R.layout.layout_list;
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_home;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_filter:
                onFilterClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getRecyclerId() {
        return R.id.lvGrid;
    }

    @Override
    protected void initView(View root) {

    }

    @Override
    protected AbstractRecyclerAdapter getAdapter() {
        return new OrderListAdapter(getActivity(), new ArrayList<Order>());
    }

    @Override
    protected Class getFilterFragmentClass() {
        return null;
    }

}


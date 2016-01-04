package com.admin.module.party.view.fragment;

import android.view.MenuItem;
import android.view.View;

import com.admin.module.party.view.adapter.SupplierListAdapter;
import com.view.adapter.recycler.AbstractRecyclerAdapter;
import com.view.fragment.AbstractRecyclerFragment;

import com.admin.R;
import model.party.Party;
import repository.party.PartyRepo;

public class SupplierListFragment extends AbstractRecyclerFragment<Party> {

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
        return new SupplierListAdapter(
                getActivity(),
                PartyRepo.getAll());
    }

    protected Class getFilterFragmentClass() {
        return SupplierFilterFragment.class;
    }

}


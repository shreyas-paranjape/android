package com.admin.module.catalogue.view.component.list;

import android.view.MenuItem;
import android.view.View;

import com.admin.module.catalogue.view.adapter.list.ProdCatListAdapter;
import com.orm.SugarRecord;
import com.view.adapter.recycler.AbstractRecyclerAdapter;
import com.view.fragment.AbstractRecyclerFragment;

import com.admin.R;
import model.catalogue.ProductCategory;

public class ProdCatListFragment extends AbstractRecyclerFragment<ProductCategory> {


    @Override
    protected int getLayoutId() {
        return R.layout.layout_list;
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
        return new ProdCatListAdapter(getActivity(), SugarRecord.listAll(ProductCategory.class));
    }


    @Override
    protected int getMenuId() {
        return R.menu.menu_home;
    }

    @Override
    protected Class getFilterFragmentClass() {
        return null;
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

}

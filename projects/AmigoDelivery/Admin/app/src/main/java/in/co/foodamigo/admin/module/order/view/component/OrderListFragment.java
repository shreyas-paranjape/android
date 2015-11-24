package in.co.foodamigo.admin.module.order.view.component;

import android.view.MenuItem;
import android.view.View;

import com.view.fragment.AbstractRecyclerFragment;
import com.view.widget.AbstractRecyclerAdapter;

import java.util.ArrayList;

import in.co.foodamigo.admin.R;
import in.co.foodamigo.admin.module.app.singleton.Constant;
import in.co.foodamigo.admin.module.order.view.adapter.OrderListAdapter;
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
        return new OrderListAdapter(getActivity(),
                applyFilters(new ArrayList<Order>(), filters));
    }

    @Override
    protected Class getFilterFragmentClass() {
        return null;
    }

    @Override
    protected String getArgumentKey() {
        return Constant.ORDER;
    }

}


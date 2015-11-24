package in.co.foodamigo.admin.module.party.view.component.list;

import android.view.MenuItem;
import android.view.View;

import com.view.fragment.AbstractRecyclerFragment;
import com.view.widget.AbstractRecyclerAdapter;

import in.co.foodamigo.admin.R;
import in.co.foodamigo.admin.module.app.singleton.Constant;
import in.co.foodamigo.admin.module.party.view.adapter.list.SupplierListAdapter;
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
                applyFilters(PartyRepo.getAll(), filters));
    }

    @Override
    protected String getArgumentKey() {
        return Constant.FILTER;
    }

    protected Class getFilterFragmentClass() {
        return SupplierFilterFragment.class;
    }

}


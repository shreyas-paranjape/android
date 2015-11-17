package in.co.foodamigo.admin.module.catalogue.view.component.list;

import android.view.MenuItem;
import android.view.View;

import com.orm.SugarRecord;
import com.util.IPredicate;
import com.view.fragment.AbstractRecyclerFragment;
import com.view.widget.AbstractRecyclerAdapter;

import java.util.Comparator;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.admin.R;
import in.co.foodamigo.admin.module.catalogue.view.adapter.list.SupplierListAdapter;
import model.party.Party;

public class SupplierListFragment extends AbstractRecyclerFragment {

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
            case R.id.action_sort:
                EventBus.getDefault().post(new SupplierListAdapter.FilterSort(
                        new IPredicate<Party>() {
                            @Override
                            public boolean apply(Party type) {
                                return type.getName().contains("1");
                            }
                        },
                        new Comparator<Party>() {
                            @Override
                            public int compare(Party lhs, Party rhs) {
                                return (int) (lhs.getId() - rhs.getId());
                            }
                        }));
                break;
            case R.id.action_filter:
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
        return new SupplierListAdapter(getActivity(),
                SugarRecord.listAll(Party.class));
    }

}


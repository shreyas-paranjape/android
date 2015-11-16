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
import in.co.foodamigo.admin.module.catalogue.view.adapter.list.ProdCatListAdapter;
import model.catalogue.ProductCategory;

public class ProdCatListFragment extends AbstractRecyclerFragment {
    private ProdCatListAdapter adapter;

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
        adapter = new ProdCatListAdapter(getActivity(),
                SugarRecord.listAll(ProductCategory.class));
        return adapter;
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
                EventBus.getDefault().post(
                        new ProdCatListAdapter.FilterSort(
                                new IPredicate<ProductCategory>() {
                                    @Override
                                    public boolean apply(ProductCategory type) {
                                        return type.getName().contains("1");
                                    }
                                },
                                new Comparator<ProductCategory>() {
                                    @Override
                                    public int compare(ProductCategory lhs, ProductCategory rhs) {
                                        return (int) (lhs.getId() - rhs.getId());
                                    }
                                }));
                break;
            case R.id.action_filter:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

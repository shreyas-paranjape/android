package in.co.foodamigo.admin.module.catalogue.view.component.list;

import android.widget.ArrayAdapter;

import com.orm.SugarRecord;

import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.admin.R;
import in.co.foodamigo.admin.module.catalogue.model.ProductCategory;
import in.co.foodamigo.admin.module.catalogue.view.adapter.list.ProdCatListAdapter;

public class ProdCatListFragment extends AbstractListFragment<ProductCategory> {

    @Override
    protected void add() {
        EventBus.getDefault().post(new ProdCatListAdapter.ProdCatEvent(null));
    }

    @Override
    protected ArrayAdapter<ProductCategory> getAdapter() {
        final List<ProductCategory> productCategories = SugarRecord.listAll(ProductCategory.class);
        return new ProdCatListAdapter(
                getActivity(),
                R.layout.item_prod_cat,
                productCategories);
    }


}

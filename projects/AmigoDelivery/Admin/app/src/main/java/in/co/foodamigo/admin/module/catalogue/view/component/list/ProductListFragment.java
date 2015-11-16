package in.co.foodamigo.admin.module.catalogue.view.component.list;

import android.widget.ArrayAdapter;

import com.orm.SugarRecord;

import in.co.foodamigo.admin.R;
import in.co.foodamigo.admin.module.catalogue.model.Product;
import in.co.foodamigo.admin.module.catalogue.view.adapter.list.ProdListAdapter;

public class ProductListFragment extends AbstractListFragment<Product> {

    @Override
    protected void add() {
    }

    @Override
    protected ArrayAdapter<Product> getAdapter() {
        return new ProdListAdapter(
                getActivity(),
                R.layout.item_prod,
                SugarRecord.listAll(Product.class));
    }


}

package in.co.foodamigo.admin.module.catalogue.view.component.list;

import android.widget.ArrayAdapter;

import com.orm.SugarRecord;

import in.co.foodamigo.admin.R;
import in.co.foodamigo.admin.module.catalogue.model.Supplier;
import in.co.foodamigo.admin.module.catalogue.view.adapter.list.SupplierListAdapter;

public class SupplierListFragment extends AbstractListFragment<Supplier> {

    @Override
    protected void add() {
    }

    @Override
    protected ArrayAdapter<Supplier> getAdapter() {
        return new SupplierListAdapter(
                getActivity(),
                R.layout.item_supplier,
                SugarRecord.listAll(Supplier.class));
    }

}

package in.co.foodamigo.admin.module.catalogue.view.adapter.spinner;

import android.content.Context;

import java.util.List;

import in.co.foodamigo.admin.module.catalogue.model.Supplier;

public class SupplierSpinnerAdapter extends AbstractSpinnerAdapter<Supplier> {

    public SupplierSpinnerAdapter(Context context, int resource, List<Supplier> objects) {
        super(context, resource, objects);
    }

    @Override
    protected String getText(Supplier item) {
        return item.getName();
    }
}

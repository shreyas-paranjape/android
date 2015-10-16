package in.co.foodamigo.admin.module.catalogue.view.adapter.spinner;

import android.content.Context;

import java.util.List;

import in.co.foodamigo.admin.module.catalogue.model.ProductCategory;

public class ProdCatSpinnerAdapter extends AbstractSpinnerAdapter<ProductCategory> {

    public ProdCatSpinnerAdapter(Context context, int resource, List<ProductCategory> objects) {
        super(context, resource, objects);
    }

    @Override
    protected String getText(ProductCategory item) {
        return item.getName();
    }
}

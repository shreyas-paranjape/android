package in.co.foodamigo.admin.module.catalogue.view.adapter.spinner;

import android.content.Context;

import com.view.widget.FormSpinnerAdapter;

import java.util.List;

import model.catalogue.ProductCategory;


public class ProdCatSpinnerAdapter extends FormSpinnerAdapter<ProductCategory> {

    public ProdCatSpinnerAdapter(Context context, int resource, List<ProductCategory> objects) {
        super(context, resource, objects);
    }

    @Override
    protected String getText(ProductCategory item) {
        return item.getName();
    }
}

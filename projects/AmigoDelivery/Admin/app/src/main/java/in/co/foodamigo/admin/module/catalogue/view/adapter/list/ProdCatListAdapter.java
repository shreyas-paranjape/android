package in.co.foodamigo.admin.module.catalogue.view.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.admin.databinding.ItemProdCatBinding;
import in.co.foodamigo.admin.module.catalogue.model.ProductCategory;

public class ProdCatListAdapter extends ArrayAdapter<ProductCategory> {

    private LayoutInflater inflater;

    public ProdCatListAdapter(Context context, int resource,
                              List<ProductCategory> productCategories) {
        super(context, resource, productCategories);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ItemProdCatBinding rootBinding =
                ItemProdCatBinding.inflate(inflater, parent, false);
        rootBinding.setCategory(getItem(position));
        rootBinding.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(
                        new ProdCatEvent(getItem(position)));
            }
        });
        return rootBinding.getRoot();
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public static class ProdCatEvent {
        private final ProductCategory productCategory;

        public ProdCatEvent(ProductCategory productCategory) {
            this.productCategory = productCategory;
        }

        public ProductCategory getProductCategory() {
            return productCategory;
        }
    }
}

package in.co.foodamigo.admin.module.catalogue.view.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.event.ChangeContentEvent;
import com.util.Util;

import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.admin.databinding.ItemProdBinding;
import in.co.foodamigo.admin.module.app.singleton.Constant;
import in.co.foodamigo.admin.module.catalogue.model.Product;
import in.co.foodamigo.admin.module.catalogue.view.component.form.ProdFormFragment;

public class ProdListAdapter extends ArrayAdapter<Product> {

    private LayoutInflater inflater;

    public ProdListAdapter(Context context, int resource, List<Product> products) {
        super(context, resource, products);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ItemProdBinding rootBinding =
                ItemProdBinding.inflate(inflater, parent, false);
        rootBinding.setProduct(getItem(position));
        rootBinding.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(
                        new ChangeContentEvent(
                                ProdFormFragment.class,
                                Util.bundleSerializable(
                                        Constant.PRODUCT_CATEGORY,
                                        getItem(position))));
            }
        });
        return rootBinding.getRoot();
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

}

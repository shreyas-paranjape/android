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
import in.co.foodamigo.admin.databinding.ItemSupplierBinding;
import in.co.foodamigo.admin.module.app.singleton.Constant;
import in.co.foodamigo.admin.module.catalogue.model.Supplier;
import in.co.foodamigo.admin.module.catalogue.view.component.form.SupplierFormFragment;

public class SupplierListAdapter extends ArrayAdapter<Supplier> {

    private LayoutInflater inflater;

    public SupplierListAdapter(Context context, int resource, List<Supplier> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ItemSupplierBinding rootBinding =
                ItemSupplierBinding.inflate(inflater, parent, false);
        rootBinding.setSupplier(getItem(position));
        rootBinding.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(
                        new ChangeContentEvent(
                                SupplierFormFragment.class,
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

package in.co.foodamigo.admin.module.party.view.adapter.spinner;

import android.content.Context;

import java.util.List;

import com.view.widget.FormSpinnerAdapter;
import model.party.Party;


public class SupplierSpinnerAdapter extends FormSpinnerAdapter<Party> {

    public SupplierSpinnerAdapter(Context context, int resource, List<Party> objects) {
        super(context, resource, objects);
    }

    @Override
    protected String getText(Party item) {
        return item.getName();
    }
}

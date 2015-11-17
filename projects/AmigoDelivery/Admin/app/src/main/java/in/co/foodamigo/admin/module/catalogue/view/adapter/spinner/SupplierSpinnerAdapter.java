package in.co.foodamigo.admin.module.catalogue.view.adapter.spinner;

import android.content.Context;

import java.util.List;

import model.party.Party;


public class SupplierSpinnerAdapter extends AbstractSpinnerAdapter<Party> {

    public SupplierSpinnerAdapter(Context context, int resource, List<Party> objects) {
        super(context, resource, objects);
    }

    @Override
    protected String getText(Party item) {
        return item.getName();
    }
}

package in.co.foodamigo.customer.module.order.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.view.adapter.list.AbstractListAdapter;

import java.util.List;

import in.co.foodamigo.customer.R;
import model.common.Location;

public class DeliveryLocationAdapter extends AbstractListAdapter<Location> {

    public DeliveryLocationAdapter(Context context, List<Location> locationList) {
        super(context, locationList);
    }

    @Override
    protected View getView(int position, ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_select_address, parent, false);
        TextView tvAddress = (TextView) root.findViewById(R.id.tvAddress);
        tvAddress.setText(getItem(position).getAddress());
        return root;
    }
}
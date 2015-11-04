package in.co.foodamigo.customer.module.order.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import delivery.model.common.Address;
import in.co.foodamigo.customer.R;

public class DeliveryAddressAdapter extends ArrayAdapter<Address> {

    protected final LayoutInflater inflater;

    public DeliveryAddressAdapter(Context context, int resource, List<Address> addressList) {
        super(context, resource, addressList);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_select_address, parent, false);
        }
        initView(convertView, position);
        return convertView;
    }

    private void initView(View root, int position) {
        TextView tvAddress = (TextView) root.findViewById(R.id.tvAddress);
        tvAddress.setText(getItem(position).getAddressString());
    }

}

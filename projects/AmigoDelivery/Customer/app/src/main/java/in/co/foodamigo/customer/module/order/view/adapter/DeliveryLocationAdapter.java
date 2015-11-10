package in.co.foodamigo.customer.module.order.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import in.co.foodamigo.customer.R;
import model.common.Location;

public class DeliveryLocationAdapter extends ArrayAdapter<Location> {

    protected final LayoutInflater inflater;

    public DeliveryLocationAdapter(Context context, int resource, List<Location> locationList) {
        super(context, resource, locationList);
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
        tvAddress.setText(getItem(position).getAddress());
    }

}

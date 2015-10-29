package in.co.foodamigo.customer.module.order.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import delivery.model.order.OrderItem;
import in.co.foodamigo.customer.databinding.ItemOrderConfirmBinding;

public class OrderItemConfirmAdapter extends ArrayAdapter<OrderItem> {

    protected final LayoutInflater inflater;

    public OrderItemConfirmAdapter(Context context, int resource, List<OrderItem> orderItemList) {
        super(context, resource, orderItemList);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemOrderConfirmBinding rootBinding = ItemOrderConfirmBinding.inflate(inflater);
        rootBinding.setOrderItem(getItem(position));
        return rootBinding.getRoot();
    }
}

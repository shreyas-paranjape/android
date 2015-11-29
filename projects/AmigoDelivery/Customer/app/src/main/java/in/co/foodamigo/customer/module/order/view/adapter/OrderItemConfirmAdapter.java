package in.co.foodamigo.customer.module.order.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.view.adapter.list.AbstractListAdapter;

import java.util.List;

import in.co.foodamigo.customer.databinding.ItemOrderConfirmBinding;
import model.order.OrderItem;

public class OrderItemConfirmAdapter extends AbstractListAdapter<OrderItem> {

    public OrderItemConfirmAdapter(Context context, List<OrderItem> orderItemList) {
        super(context, orderItemList);
    }

    @Override
    protected View getView(int position, ViewGroup parent) {
        ItemOrderConfirmBinding rootBinding = ItemOrderConfirmBinding.inflate(inflater);
        rootBinding.setOrderItem(getItem(position));
        return rootBinding.getRoot();
    }
}

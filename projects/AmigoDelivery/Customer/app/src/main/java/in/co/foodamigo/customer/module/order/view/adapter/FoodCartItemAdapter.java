package in.co.foodamigo.customer.module.order.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.view.adapter.list.OrderItemAdapter;

import java.util.List;

import in.co.foodamigo.customer.databinding.ItemOrderCurrentBinding;
import model.order.OrderItem;

public class FoodCartItemAdapter extends OrderItemAdapter {

    public FoodCartItemAdapter(Context context, List<OrderItem> orderItems) {
        super(context, orderItems);
    }

    @Override
    protected View getView(final int position, ViewGroup parent) {
        final ItemOrderCurrentBinding rootBinding =
                ItemOrderCurrentBinding.inflate(inflater, parent, false);
        rootBinding.setOrderItem(getItem(position));
        rootBinding.btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(getItem(position).getProduct());
            }
        });
        rootBinding.btnRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(getItem(position).getProduct());
            }
        });
        return rootBinding.getRoot();
    }

}

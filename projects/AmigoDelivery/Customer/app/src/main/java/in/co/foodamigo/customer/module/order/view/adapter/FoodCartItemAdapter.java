package in.co.foodamigo.customer.module.order.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.order.CartItemAdapter;

import java.util.List;

import in.co.foodamigo.customer.databinding.ItemOrderCurrentBinding;
import model.order.OrderItem;

public class FoodCartItemAdapter extends CartItemAdapter {

    public FoodCartItemAdapter(Context context, int resource, List<OrderItem> orderItems) {
        super(context, resource, orderItems);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ItemOrderCurrentBinding rootBinding =
                ItemOrderCurrentBinding.inflate(inflater, parent, false);
        initView(rootBinding, position);
        return rootBinding.getRoot();
    }

    private void initView(ItemOrderCurrentBinding rootBinding, final int position) {
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
    }

}

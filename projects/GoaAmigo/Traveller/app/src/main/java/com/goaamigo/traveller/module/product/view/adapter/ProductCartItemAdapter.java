package com.goaamigo.traveller.module.product.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.databinding.ItemOrderCurrentBinding;
import com.view.adapter.list.OrderItemAdapter;

import java.util.List;

import model.order.OrderItem;

public class ProductCartItemAdapter extends OrderItemAdapter {

    public ProductCartItemAdapter(Context context, List<OrderItem> orderItems) {
        super(context, orderItems);
    }

    @Override
    protected View getView(final int position, ViewGroup parent) {
        final ItemOrderCurrentBinding rootBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_order_current, parent, false);
        rootBinding.setOrderItem(getItem(position));
        rootBinding.btnRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(getItem(position).getProduct());
            }
        });
        return rootBinding.getRoot();
    }


}


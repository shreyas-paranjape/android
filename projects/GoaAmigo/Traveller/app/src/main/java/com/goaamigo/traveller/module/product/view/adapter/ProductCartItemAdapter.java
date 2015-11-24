package com.goaamigo.traveller.module.product.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.goaamigo.traveller.databinding.ItemOrderCurrentBinding;
import com.order.CartItemAdapter;

import java.util.List;

import model.order.OrderItem;

public class ProductCartItemAdapter extends CartItemAdapter {

    public ProductCartItemAdapter(Context context, List<OrderItem> orderItems) {
        super(context, 0, orderItems);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View toReturn;
        if (convertView == null) {
            final ItemOrderCurrentBinding rootBinding =
                    ItemOrderCurrentBinding.inflate(inflater, parent, false);
            rootBinding.setOrderItem(getItem(position));
            rootBinding.btnRemoveItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(getItem(position).getProduct());
                }
            });
            toReturn = rootBinding.getRoot();
        } else {
            toReturn = convertView;
        }
        return toReturn;
    }


}


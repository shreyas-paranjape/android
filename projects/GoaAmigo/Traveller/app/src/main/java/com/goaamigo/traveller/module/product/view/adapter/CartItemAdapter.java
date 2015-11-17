package com.goaamigo.traveller.module.product.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.Contoller.CurrentOrderManager;

import java.util.List;

import de.greenrobot.event.EventBus;
import model.catalogue.Product;
import model.order.OrderItem;

public class CartItemAdapter extends ArrayAdapter<OrderItem> {

    protected final LayoutInflater inflater;
    TextView itemName,itemPrice,itemQuantity;
    Button btnAddItem,btnRemoveItem;
    public CartItemAdapter(Context context, int resource, List<OrderItem> orderItems) {
        super(context, resource, orderItems);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_order_current, parent, false);
        }
        itemName = (TextView) convertView.findViewById(R.id.tv_item_order_product_name);
        itemPrice = (TextView) convertView.findViewById(R.id.tv_item_order_product_price);
        itemQuantity = (TextView) convertView.findViewById(R.id.tv_item_order_quantity);
        btnAddItem = (Button) convertView.findViewById(R.id.btnAddItem);
        btnRemoveItem = (Button) convertView.findViewById(R.id.btnRemoveItem);


        initView(convertView, position);
//        return rootBinding.getRoot();
        return  convertView;

    }

    private void initView(View v, final int position) {

//        rootBinding.setOrderItem(getItem(position));
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(getItem(position).getProduct());
            }
        });

        btnRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(getItem(position).getProduct());
            }
        });
    }

    private void addItem(Product product) {
        modifyItem(product, CurrentOrderManager.CartAction.ADD);
    }

    private void removeItem(Product product) {
        modifyItem(product, CurrentOrderManager.CartAction.REMOVE);
    }

    private void modifyItem(Product product, CurrentOrderManager.CartAction action) {
        EventBus.getDefault().post(
                new CurrentOrderManager.ModifyCartEvent(product, action));
    }
}


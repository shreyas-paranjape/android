package in.co.foodamigo.customer.module.order.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import de.greenrobot.event.EventBus;
import delivery.model.catalogue.ProductParty;
import delivery.model.order.OrderItem;
import in.co.foodamigo.customer.databinding.ItemOrderCurrentBinding;
import in.co.foodamigo.customer.module.order.controller.OrderManager;

public class CartItemAdapter extends ArrayAdapter<OrderItem> {

    protected final LayoutInflater inflater;

    public CartItemAdapter(Context context, int resource, List<OrderItem> orderItems) {
        super(context, resource, orderItems);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        rootBinding.btnAddQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyItem(getItem(position).getProductParty(), OrderManager.CartAction.ADD);
            }
        });

        rootBinding.btnRemoveQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyItem(getItem(position).getProductParty(), OrderManager.CartAction.REMOVE);
            }
        });
    }

    private void modifyItem(ProductParty productParty, OrderManager.CartAction action) {
        EventBus.getDefault().post(
                new OrderManager.ModifyCartEvent(productParty, action));
    }
}

package in.co.foodamigo.customer.module.order.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.customer.databinding.ItemOrderCurrentBinding;
import in.co.foodamigo.customer.module.order.controller.CurrentOrderManager;
import model.catalogue.Product;
import model.order.OrderItem;

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

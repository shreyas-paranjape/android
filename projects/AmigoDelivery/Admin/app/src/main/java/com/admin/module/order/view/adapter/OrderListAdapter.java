package com.admin.module.order.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.util.Constant;
import com.view.adapter.recycler.AbstractRecyclerAdapter;

import java.util.Comparator;
import java.util.List;

import com.admin.databinding.ItemOrderBinding;
import com.admin.module.catalogue.view.component.form.ProdFormFragment;
import de.greenrobot.event.EventBus;
import model.order.Order;

public class OrderListAdapter
        extends AbstractRecyclerAdapter<Order, OrderListAdapter.ViewHolder> {

    public OrderListAdapter(Context context, List<Order> orders) {
        super(context, orders);
        EventBus.getDefault().register(this);
    }

    @Override
    protected String getCacheFilterKey() {
        return Constant.ORDER;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemOrderBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.binding.setOrder(getItem(position));
        holder.binding.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderListAdapter.this
                        .onClick(ProdFormFragment.class,
                                Constant.ORDER,
                                getItem(position));
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ItemOrderBinding binding;

        public ViewHolder(ItemOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void onEvent(OrderSort event) {
        sort(event.getComparator());
    }

    public static class OrderSort extends Sort<Order> {

        public OrderSort(Comparator<Order> comparator) {
            super(comparator);
        }
    }
}

package in.co.foodamigo.admin.module.order.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.view.widget.AbstractRecyclerAdapter;

import java.util.Comparator;
import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.admin.databinding.ItemOrderBinding;
import in.co.foodamigo.admin.module.app.singleton.Constant;
import in.co.foodamigo.admin.module.catalogue.view.component.form.ProdFormFragment;
import model.order.Order;

public class OrderListAdapter
        extends AbstractRecyclerAdapter<Order, OrderListAdapter.ViewHolder> {

    public OrderListAdapter(Context context, List<Order> orders) {
        super(context, orders);
        EventBus.getDefault().register(this);
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

    public void onEvent(Sort event) {
        sort(event.getComparator());
    }

    public static class Sort {
        private final Comparator<Order> comparator;

        public Sort(Comparator<Order> comparator) {
            this.comparator = comparator;
        }

        public Comparator<Order> getComparator() {
            return comparator;
        }

    }
}

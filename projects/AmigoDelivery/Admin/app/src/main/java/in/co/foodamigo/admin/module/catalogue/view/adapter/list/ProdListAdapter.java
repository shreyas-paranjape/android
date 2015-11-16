package in.co.foodamigo.admin.module.catalogue.view.adapter.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.util.IPredicate;
import com.view.widget.AbstractRecyclerAdapter;

import java.util.Comparator;
import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.admin.databinding.ItemProdBinding;
import in.co.foodamigo.admin.module.app.singleton.Constant;
import in.co.foodamigo.admin.module.catalogue.view.component.form.ProdFormFragment;
import model.catalogue.Product;

public class ProdListAdapter extends AbstractRecyclerAdapter<Product, ProdListAdapter.ViewHolder> {

    public ProdListAdapter(Context context, List<Product> products) {
        super(context, products);
        EventBus.getDefault().register(this);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemProdBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.binding.setProduct(getItem(position));
        holder.binding.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProdListAdapter.this
                        .onClick(ProdFormFragment.class,
                                Constant.PRODUCT_CATEGORY,
                                getItem(position));
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ItemProdBinding binding;

        public ViewHolder(ItemProdBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void onEvent(FilterSort event) {
        filterSort(event.getPredicate(), event.getComparator());
    }

    public static class FilterSort {
        private final IPredicate<Product> predicate;
        private final Comparator<Product> comparator;

        public FilterSort(IPredicate<Product> predicate,
                          Comparator<Product> comparator) {
            this.predicate = predicate;
            this.comparator = comparator;
        }

        public Comparator<Product> getComparator() {
            return comparator;
        }

        public IPredicate<Product> getPredicate() {
            return predicate;
        }
    }
}

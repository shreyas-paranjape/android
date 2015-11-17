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
import in.co.foodamigo.admin.databinding.ItemProdCatBinding;
import in.co.foodamigo.admin.module.app.singleton.Constant;
import in.co.foodamigo.admin.module.catalogue.view.component.form.ProdCatFormFragment;
import model.catalogue.ProductCategory;

public class ProdCatListAdapter
        extends AbstractRecyclerAdapter<ProductCategory, ProdCatListAdapter.ViewHolder> {

    public ProdCatListAdapter(Context context,
                              List<ProductCategory> productCategories) {
        super(context, productCategories);
        EventBus.getDefault().register(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemProdCatBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.binding.setCategory(getItem(position));
        holder.binding.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProdCatListAdapter.this
                        .onClick(ProdCatFormFragment.class,
                                Constant.PRODUCT_CATEGORY,
                                getItem(position));
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ItemProdCatBinding binding;

        public ViewHolder(ItemProdCatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void onEvent(FilterSort event) {
        filterSort(event.getPredicate(), event.getComparator());
    }

    public static class FilterSort {
        private final IPredicate<ProductCategory> predicate;
        private final Comparator<ProductCategory> comparator;

        public FilterSort(IPredicate<ProductCategory> predicate,
                          Comparator<ProductCategory> comparator) {
            this.predicate = predicate;
            this.comparator = comparator;
        }

        public Comparator<ProductCategory> getComparator() {
            return comparator;
        }

        public IPredicate<ProductCategory> getPredicate() {
            return predicate;
        }
    }


}

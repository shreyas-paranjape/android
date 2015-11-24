package in.co.foodamigo.admin.module.catalogue.view.adapter.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.util.Constant;
import com.view.widget.AbstractRecyclerAdapter;

import java.util.Comparator;
import java.util.List;

import in.co.foodamigo.admin.databinding.ItemProdCatBinding;
import in.co.foodamigo.admin.module.catalogue.view.component.form.ProdCatFormFragment;
import model.catalogue.ProductCategory;

public class ProdCatListAdapter
        extends AbstractRecyclerAdapter<ProductCategory, ProdCatListAdapter.ViewHolder> {

    public ProdCatListAdapter(Context context,
                              List<ProductCategory> productCategories) {
        super(context, productCategories);
    }

    @Override
    protected String getCacheFilterKey() {
        return Constant.PRODUCT_CATEGORY;
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

    public void onEvent(ProductCategorySort event) {
        sort(event.getComparator());
    }

    public static class ProductCategorySort extends Sort<ProductCategory> {

        public ProductCategorySort(Comparator<ProductCategory> comparator) {
            super(comparator);
        }
    }


}

package in.co.foodamigo.admin.module.catalogue.view.adapter.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.util.Constant;
import com.view.widget.AbstractRecyclerAdapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.admin.databinding.ItemProdBinding;
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

    public void onEvent(ProductSort event) {
        sort(event.getComparator());
    }

    public static class ProductSort extends AbstractRecyclerAdapter.Sort<Product> {

        public ProductSort(Comparator<Product> comparator) {
            super(comparator);
        }
    }

    @Override
    protected List<Product> cloneList(List<Product> list) {
        List<Product> clone = new ArrayList<>(list.size());
        for (Product item : list) {
            clone.add(item.clone());
        }
        return clone;
    }

    @Override
    protected String getCacheFilterKey() {
        return Constant.PRODUCT;
    }
}

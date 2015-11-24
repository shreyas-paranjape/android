package in.co.foodamigo.admin.module.party.view.adapter.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.view.widget.AbstractRecyclerAdapter;

import java.util.Comparator;
import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.admin.databinding.ItemSupplierBinding;
import in.co.foodamigo.admin.module.app.singleton.Constant;
import in.co.foodamigo.admin.module.catalogue.view.component.form.ProdFormFragment;
import model.party.Party;

public class SupplierListAdapter
        extends AbstractRecyclerAdapter<Party, SupplierListAdapter.ViewHolder> {

    public SupplierListAdapter(Context context, List<Party> parties) {
        super(context, parties);
        EventBus.getDefault().register(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ItemSupplierBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.binding.setSupplier(getItem(position));
        holder.binding.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SupplierListAdapter.this
                        .onClick(ProdFormFragment.class,
                                Constant.PRODUCT_CATEGORY,
                                getItem(position));
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ItemSupplierBinding binding;

        public ViewHolder(ItemSupplierBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void onEvent(Sort event) {
        sort(event.getComparator());
    }

    public static class Sort {
        private final Comparator<Party> comparator;

        public Sort(Comparator<Party> comparator) {
            this.comparator = comparator;
        }

        public Comparator<Party> getComparator() {
            return comparator;
        }

    }
}

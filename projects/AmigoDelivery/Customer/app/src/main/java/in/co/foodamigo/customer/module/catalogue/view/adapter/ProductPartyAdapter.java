package in.co.foodamigo.customer.module.catalogue.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.greenrobot.event.EventBus;
import delivery.model.catalogue.ProductCategory;
import delivery.model.catalogue.ProductParty;
import delivery.repository.ProdRepo;
import in.co.foodamigo.customer.databinding.ItemProductBinding;
import in.co.foodamigo.customer.module.order.controller.OrderManager;

public class ProductPartyAdapter
        extends RecyclerView.Adapter<ProductPartyAdapter.ViewHolder> {

    protected final LayoutInflater inflater;
    protected final List<ProductParty> data;

    public ProductPartyAdapter(Context context, ProductCategory productCategory) {
        this.data = ProdRepo.findByCategory(productCategory);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(ItemProductBinding.inflate(inflater));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final ProductParty productParty = data.get(i);
        viewHolder.productCardView.setProductParty(productParty);
        viewHolder.productCardView.btnProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(
                        new OrderManager.ModifyCartEvent(productParty, OrderManager.CartAction.ADD));
            }
        });
        //TODO Load image from URL
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ItemProductBinding productCardView;

        public ViewHolder(ItemProductBinding productCardView) {
            super(productCardView.getRoot());
            this.productCardView = productCardView;
        }
    }

}
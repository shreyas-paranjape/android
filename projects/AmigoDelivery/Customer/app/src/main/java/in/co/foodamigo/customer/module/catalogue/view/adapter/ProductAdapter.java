package in.co.foodamigo.customer.module.catalogue.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.order.CurrentOrderManager;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.customer.databinding.ItemProductBinding;
import model.catalogue.Product;
import model.catalogue.ProductCategory;
import repository.catalogue.ProductRepo;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    protected final LayoutInflater inflater;
    protected List<Product> data = new ArrayList<>();
    private final Context context;

    public ProductAdapter(Context context, ProductCategory productCategory) {
        this.context = context;
        if (productCategory != null) {
            this.data = ProductRepo.getForCategoryWithSub(productCategory.getId());
        }
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(ItemProductBinding.inflate(inflater, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Product product = data.get(i);
        viewHolder.productCardView.setProduct(product);
        viewHolder.productCardView.btnProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(
                        new CurrentOrderManager.ModifyCartEvent(
                                product,
                                CurrentOrderManager.CartAction.ADD));
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
package in.co.foodamigo.customer.module.catalogue.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.order.CurrentOrderManager;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.customer.databinding.ItemProductBinding;
import model.catalogue.Product;

public class ProductAdapter
        extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    protected final LayoutInflater inflater;
    protected final List<Product> data;
    private final Context context;

    public ProductAdapter(Context context, List<Product> data) {
        this.context = context;
        this.data = data;
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
                        new CurrentOrderManager.ModifyCartEvent(product, CurrentOrderManager.CartAction.ADD));
            }
        });
        Picasso.with(context)
                .load(product.getImageUrl())
                .into(viewHolder.productCardView.imgProduct, new Callback.EmptyCallback() {
                    @Override
                    public void onError() {
                        Log.d("ProductAdapter", "Could not load image");
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
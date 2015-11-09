package com.goaamigo.traveller.module.product.view.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.goaamigo.model.trip.catalogue.Product;
import com.goaamigo.traveller.R;

import java.io.Serializable;
import java.util.List;

import de.greenrobot.event.EventBus;

public class ProductAdapter
        extends RecyclerView.Adapter<ProductAdapter.ViewHolder>
        implements Serializable {

    private static final long serialVersionUID = 1l;
    private final List<Product> productsList;

    public ProductAdapter(List<Product> productsList) {
        this.productsList = productsList;
    }

    public ProductAdapter() {
        this.productsList = null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        ViewHolder phv = new ViewHolder(v);
        return phv;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(productsList.get(position).getName());
        holder.location.setText(productsList.get(position).getProductLocation());
        holder.rating.setText(productsList.get(position).getProductRating());
        holder.discount.setText("-" + productsList.get(position).getProductDiscount());
        holder.price.setText("Rs." + productsList.get(position).getProductPrice());
        holder.imageView.setImageResource(productsList.get(position).getProductImage());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public List<Product> getProducts() {
        return productsList;
    }

//    public void addProduct(Product product){
//        productsList.add(product);
//        EventBus.getDefault().post(new ProductDataSetChanged());
//    }

    /*@Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        EventBus.getDefault().post(new ProductDataSetChanged());
    }*/

    public static class ProductDataSetChanged {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, location, rating, discount, price;
        ImageView imageView;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.productName);
            location = (TextView) itemView.findViewById(R.id.productLocation);
            rating = (TextView) itemView.findViewById(R.id.productRating);
            discount = (TextView) itemView.findViewById(R.id.productDiscount);
            price = (TextView) itemView.findViewById(R.id.productPrice);
            imageView = (ImageView) itemView.findViewById(R.id.product_image);
            cv = (CardView) itemView.findViewById(R.id.cvProduct);
        }
    }
}

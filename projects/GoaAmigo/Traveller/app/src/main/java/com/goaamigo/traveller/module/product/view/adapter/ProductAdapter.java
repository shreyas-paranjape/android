package com.goaamigo.traveller.module.product.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.event.ChangeContentEvent;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.fragment.RestaurantFragment;
import com.util.Constant;
import com.view.adapter.recycler.AbstractRecyclerAdapter;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import de.greenrobot.event.EventBus;
import model.catalogue.Product;

public class ProductAdapter
        extends AbstractRecyclerAdapter<Product, ProductAdapter.ViewHolder>
        implements Serializable {

    private static final long serialVersionUID = 1l;

    public ProductAdapter(Context context, List<Product> itemList) {
        super(context, itemList);
        EventBus.getDefault().register(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.restName.setText(getItem(position).getName());
        holder.restPrice.setText("Rs." + getItem(position).getDetail().getPrice());
        holder.lv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("PRODUCT", getItem(position));
                EventBus.getDefault().post(new
                        ChangeContentEvent(RestaurantFragment.class, bundle));
            }
        });
        //holder.imageView.setImageResource(productsList.get(position).getProductImage());
        //holder.location.setText(productsList.get(position).getProductLocation());
        //holder.rating.setText((int) getItem(position).getDetail().getRating());
        //holder.discount.setText("-" + productsList.get(position).getProductDiscount());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView restName, restLocation, rating, restDiscount, restPrice;
        protected ImageView restImage;
        protected LinearLayout lv;

        public ViewHolder(View itemView) {
            super(itemView);
            restName = (TextView) itemView.findViewById(R.id.productName);
            restLocation = (TextView) itemView.findViewById(R.id.productLocation);
            rating = (TextView) itemView.findViewById(R.id.productRating);
            restDiscount = (TextView) itemView.findViewById(R.id.productDiscount);
            restPrice = (TextView) itemView.findViewById(R.id.productPrice);
            restImage = (ImageView) itemView.findViewById(R.id.product_image);
            lv = (LinearLayout) itemView.findViewById(R.id.ll_card);
        }
    }

    @SuppressWarnings("unused")
    public void onEvent(ProductSort event) {
        sort(event.getComparator());
    }

    public static class ProductSort extends AbstractRecyclerAdapter.Sort<Product> {

        public ProductSort(Comparator<Product> comparator) {
            super(comparator);
        }
    }

    @Override
    protected String getCacheFilterKey() {
        return Constant.PRODUCT;
    }
}

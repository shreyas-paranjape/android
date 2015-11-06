package com.goaamigo.traveller.module.product.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.goaamigo.model.trip.catalogue.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter
        extends RecyclerView.Adapter<ProductAdapter.ViewHolder>
        implements Serializable {

    private static final long serialVersionUID = 1l;
    private final List<Product> productsList = new ArrayList<>();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public List<Product> getProducts() {
        return productsList;
    }



    /*@Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        EventBus.getDefault().post(new ProductDataSetChanged());
    }*/

    public static class ProductDataSetChanged {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

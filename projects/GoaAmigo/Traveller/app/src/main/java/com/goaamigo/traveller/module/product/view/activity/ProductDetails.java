package com.goaamigo.traveller.module.product.view.activity;

import android.app.Fragment;
import android.os.Bundle;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.fragment.RestaurantFragment;
import com.view.activity.AbstractActivity;

import model.catalogue.Product;

public class ProductDetails extends AbstractActivity {
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected int getLayoutId() {
        return R.layout.activity_product_details;
    }

    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected Fragment getInitContent() {
        return new RestaurantFragment();
    }

    protected int getContentContainerId() {
        return R.id.container;
    }
}

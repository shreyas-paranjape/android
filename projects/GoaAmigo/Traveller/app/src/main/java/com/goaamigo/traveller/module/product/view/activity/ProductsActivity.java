package com.goaamigo.traveller.module.product.view.activity;

import android.app.Fragment;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.adapter.ProductAdapter;
import com.goaamigo.traveller.module.product.view.fragment.ProductListFragment;
import com.view.activity.AbstractActivity;

public class ProductsActivity extends AbstractActivity {

    private ProductAdapter productAdapter;

    protected int getLayoutId() {
        return R.layout.activity_products;
    }


    protected int getToolbarId() {
        return R.id.toolbar;
    }

    protected Fragment getInitContent() {
        return new ProductListFragment();
    }

    protected int getContentContainerId() {
        return R.id.container;
    }

}

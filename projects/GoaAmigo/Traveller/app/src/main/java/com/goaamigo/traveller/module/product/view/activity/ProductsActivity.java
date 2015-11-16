package com.goaamigo.traveller.module.product.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.Contoller.CartManager;
import com.goaamigo.traveller.module.product.view.adapter.ProductAdapter;
import com.goaamigo.traveller.module.product.view.fragment.OrderFragment;
import com.goaamigo.traveller.module.product.view.fragment.ProductListFragment;
import com.goaamigo.traveller.module.product.view.fragment.ProductMapFragment;
import com.goaamigo.traveller.module.trip.view.component.DetailsFragment;
import com.goaamigo.traveller.module.trip.view.component.SearchTripFragment;
import com.goaamigo.traveller.module.trip.view.component.TripResultsFragment;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.view.activity.AbstractActivity;

public class ProductsActivity extends AbstractActivity {
    private boolean mapsIcon = false;
    private final ProductAdapter productAdapter = new ProductAdapter();
    private final EventListener listener = new EventListener();

    private class EventListener {
    }

    private CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Spinner spinner = (Spinner) findViewById(R.id.productSpinner);
        cartManager = new CartManager((SlidingUpPanelLayout) findViewById(R.id.sliding_layout));
        cartManager.hidePanel();
        registerListener(cartManager);
        addCartFragment();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Menu_Search_Content, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void addCartFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.cartContainer, new OrderFragment()).commit();
    }

    @Override
    protected void onDestroy() {
        unRegisterListener(cartManager);
        super.onDestroy();
    }

    protected int getLayoutId() {
        return R.layout.activity_products;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_sort:
                break;
            case R.id.action_filter:
                break;
            case R.id.action_maps:
                if (mapsIcon == false) {
                    replaceContent(getMapFragment());
                    mapsIcon = true;
                } else {
                    replaceContent(getListFragment());
                    mapsIcon = false;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_products, menu);
        return true;
    }

    protected int getToolbarId() {
        return R.id.toolbar;
    }

    protected Fragment getInitContent() {
        return getListFragment();
    }

    private void addArguments(Fragment frag) {
        Bundle b = new Bundle();
        b.putSerializable("PRODUCT_ADAPTER", productAdapter);
        frag.setArguments(b);
    }

    private Fragment getMapFragment() {
        ProductMapFragment frag = new ProductMapFragment();
//        productAdapter.addProduct(new Product());
        addArguments(frag);
        return frag;
    }

    private Fragment getListFragment() {
        ProductListFragment frag = new ProductListFragment();
        addArguments(frag);
        return frag;
    }

    protected int getContentContainerId() {
        return R.id.container;
    }
}

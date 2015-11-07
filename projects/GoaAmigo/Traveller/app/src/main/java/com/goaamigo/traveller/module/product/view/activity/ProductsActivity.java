package com.goaamigo.traveller.module.product.view.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.goaamigo.model.trip.catalogue.Product;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.adapter.ProductAdapter;
import com.goaamigo.traveller.module.product.view.fragment.ProductListFragment;
import com.goaamigo.traveller.module.product.view.fragment.ProductMapFragment;
import com.view.activity.AbstractActivity;

public class ProductsActivity extends AbstractActivity {
    private Menu menu;
    private Spinner productList;
    private boolean mapsIcon = false;
    private final ProductAdapter productAdapter = new ProductAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Spinner spinner = (Spinner) findViewById(R.id.productSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Menu_Search_Content, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    protected int getLayoutId() {
        return R.layout.activity_products;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_sort:
                break;
            case R.id.action_filter:
                break;
            case R.id.action_maps:
                replaceContent(getMapFragment());
                if(mapsIcon == false){
                    menu.getItem(R.id.action_maps).setIcon(getResources().getDrawable(R.drawable.ic_map_black_24dp));
                }else {
                    menu.getItem(R.id.action_maps).setIcon(getResources().getDrawable(R.drawable.ic_home_black_24dp));
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_products, menu);
        this.menu = menu;
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
        b.putSerializable("PRODUCT_ADAPTER",productAdapter);
        frag.setArguments(b);
    }

    private Fragment getMapFragment(){
        ProductMapFragment frag = new ProductMapFragment();
        productAdapter.addProduct(new Product());
        addArguments(frag);
        return frag;
    }

    private Fragment getListFragment(){
        ProductListFragment frag = new ProductListFragment();
        addArguments(frag);
        return frag;
    }

    protected int getContentContainerId() {
        return R.id.container;
    }

}

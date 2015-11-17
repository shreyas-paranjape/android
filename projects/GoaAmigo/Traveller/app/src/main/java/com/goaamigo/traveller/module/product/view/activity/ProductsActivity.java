package com.goaamigo.traveller.module.product.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.event.ChangeContentEvent;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.Contoller.CartManager;
import com.goaamigo.traveller.module.product.view.adapter.ProductAdapter;
import com.goaamigo.traveller.module.product.view.fragment.FragmentSelectProduct;
import com.goaamigo.traveller.module.product.view.fragment.OrderFragment;
import com.goaamigo.traveller.module.product.view.fragment.ProductListFragment;
import com.goaamigo.traveller.module.product.view.fragment.ProductMapFragment;
import com.goaamigo.traveller.module.trip.view.component.DetailsFragment;
import com.goaamigo.traveller.module.trip.view.component.SearchTripFragment;
import com.goaamigo.traveller.module.trip.view.component.TripResultsFragment;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.view.activity.AbstractActivity;

import de.greenrobot.event.EventBus;

public class ProductsActivity extends AbstractActivity {
    private boolean mapsIcon = false;
    private final ProductAdapter productAdapter = new ProductAdapter();
    private final EventListener listener = new EventListener();
    LinearLayout layoutSpinner;

    String[] spinnerValues = {"trip", "hotel", "beach", "ride", "activities",};

    int total_images[] = {
            R.drawable.ic_help_black_24dp,
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_accessibility_black_24dp,
            R.drawable.ic_account_circle_black_24dp,
            R.drawable.ic_add_shopping_cart_black_24dp,
            R.drawable.ic_sort_black_24dp};

    private class EventListener {
    }

    private CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartManager = new CartManager((SlidingUpPanelLayout) findViewById(R.id.sliding_layout));
        //cartManager.hidePanel();
        registerListener(cartManager);
        addCartFragment();

        layoutSpinner = (LinearLayout) findViewById(R.id.productToolbarSelectItem);
        layoutSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                EventBus.getDefault().post(
                        new ChangeContentEvent(ChangeContentEvent.ContentType.FRAGMENT, bundle){
                            @Override
                            public Class getContentClass() {
                                return FragmentSelectProduct.class;
                            }
                        }
                );
            }
        });
//        Spinner spinner = (Spinner) findViewById(R.id.productSpinner);
//
//        spinner.setAdapter(new MyAdapter(this, R.layout.spinner_layout,
//                spinnerValues));
    }

    public class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context ctx, int txtViewResourceId, String[] objects) {
            super(ctx, txtViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
            return getCustomView(position, cnvtView, prnt);
        }

        @Override
        public View getView(int pos, View cnvtView, ViewGroup prnt) {
            return getCustomView(pos, cnvtView, prnt);
        }

        public View getCustomView(int position, View convertView,
                                  ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View mySpinner = inflater.inflate(R.layout.spinner_layout, parent,
                    false);
            TextView main_text = (TextView) mySpinner
                    .findViewById(R.id.text_main_seen);
            main_text.setText(spinnerValues[position]);

            ImageView left_icon = (ImageView) mySpinner
                    .findViewById(R.id.left_pic);
            left_icon.setImageResource(total_images[position]);

            return mySpinner;
        }
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

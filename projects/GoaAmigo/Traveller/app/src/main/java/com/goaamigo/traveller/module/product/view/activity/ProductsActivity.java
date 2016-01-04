package com.goaamigo.traveller.module.product.view.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.cache.ObjectCache;
import com.event.ChangeContentEvent;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.Filter.fragment.FilterCategory;
import com.goaamigo.traveller.module.product.view.Filter.fragment.LocalTransportRental;
import com.goaamigo.traveller.module.product.view.Filter.fragment.LocalTransportTaxi;
import com.goaamigo.traveller.module.product.view.adapter.ProductAdapter;
import com.goaamigo.traveller.module.product.view.adapter.SingleChoiceDialog;
import com.goaamigo.traveller.module.product.view.fragment.ProductListFragment;
import com.goaamigo.traveller.module.product.view.fragment.TripFilter;
import com.util.Constant;
import com.util.IPredicate;
import com.view.activity.AbstractActivity;
import com.view.adapter.spinner.ItemSpinnerAdapter;
import com.view.model.Filter;
import com.view.model.Item;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import model.catalogue.Product;

public class ProductsActivity extends AbstractActivity {

    protected final static List<Item> spinnerItems = new ArrayList<>();
    protected List<Filter<Product>> filters = new ArrayList<>();


    public ProductsActivity() {
        Object cachedFilters = ObjectCache.get(Constant.PRODUCT);
        if (cachedFilters != null) {
            filters = (List<Filter<Product>>) cachedFilters;
        } else {
            filters = new ArrayList<>();
            ObjectCache.put(Constant.PRODUCT, filters);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Spinner spinner = (Spinner) findViewById(R.id.productSpinner);
        final ItemSpinnerAdapter adapter = new ItemSpinnerAdapter(this, spinnerItems);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {
                if (spinnerItems.get(i).getName().equals("Trip")) {
                    EventBus.getDefault().post(new ChangeContentEvent(TripFilter.class));
                } else {
                    Bundle b = new Bundle();
                    FilterCategory filterCategory = new FilterCategory();
                    b.putString("Key",spinnerItems.get(i).getName());
                    filterCategory.setArguments(b);
                    switch (spinnerItems.get(i).getName()) {
                        case "Stay":
                            initCart(R.id.sliding_layout, R.id.cartContainer, filterCategory);
                            cartManager.collapsePanel();
                            break;
                        case "Restaurant":
                            initCart(R.id.sliding_layout, R.id.cartContainer, filterCategory);
                            cartManager.collapsePanel();
                            break;
                        case "Activities":
                            initCart(R.id.sliding_layout, R.id.cartContainer, filterCategory);
                            cartManager.collapsePanel();
                            break;
                        case "LocalTransport_taxi":
                            initCart(R.id.sliding_layout, R.id.cartContainer, new LocalTransportTaxi());
                            cartManager.collapsePanel();
                            break;
                        case "LocalTransport_rental":
                            initCart(R.id.sliding_layout, R.id.cartContainer, new LocalTransportRental());
                            cartManager.collapsePanel();
                            break;
                    }
                    clearFilter();
                    addFilter(new Filter<>("xyz", new IPredicate<Product>() {
                        @Override
                        public boolean apply(Product product) {
                            return product.getProductCategory().getName().contains(spinnerItems.get(i).getName());
                        }
                    }, null));
                    EventBus.getDefault().post(new ProductAdapter.FilterEvent());
                    EventBus.getDefault().post(new ProductListFragment.MessageEvent(spinnerItems.get(i).getName()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    public static class SlidingTabDismissEvent {

        public SlidingTabDismissEvent() {
        }
    }

    public void onEvent(ProductsActivity.SlidingTabDismissEvent event) {
        cartManager.collapsePanel();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sort:
                SingleChoiceDialog singleChoiceDialog = new SingleChoiceDialog();
                singleChoiceDialog.show(getFragmentManager(),"Dialog");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_products, menu);
        return true;
    }

    static {
        spinnerItems.add(new Item("Stay", R.drawable.ic_favorite_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        spinnerItems.add(new Item("Restaurant", R.drawable.ic_favorite_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        spinnerItems.add(new Item("Activities", R.drawable.ic_favorite_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        spinnerItems.add(new Item("LocalTransport_taxi", R.drawable.ic_favorite_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        spinnerItems.add(new Item("LocalTransport_rental", R.drawable.ic_favorite_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        spinnerItems.add(new Item("Trip", R.drawable.ic_favorite_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
    }

    private void addFilter(Filter filter) {
        filters.add(filter);
    }

    private void clearFilter() {
        filters.clear();
    }

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

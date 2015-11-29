package com.goaamigo.traveller.module.product.view.activity;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.event.ChangeContentEvent;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.adapter.ProductAdapter;
import com.goaamigo.traveller.module.product.view.fragment.ProductFilter;
import com.goaamigo.traveller.module.product.view.fragment.ProductListFragment;
import com.goaamigo.traveller.module.product.view.fragment.ProductOrderFragment;
import com.order.CartManager;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.view.activity.AbstractActivity;
import com.view.adapter.spinner.ItemSpinnerAdapter;
import com.view.model.Item;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class ProductsActivity extends AbstractActivity {
    private boolean mapsIcon = false;
    private CartManager cartManager;
    private final ProductAdapter productAdapter = new ProductAdapter();
    private final EventListener listener = new EventListener();
    protected final static List<Item> spinnerItems = new ArrayList<>();

    private class EventListener {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartManager = new CartManager((SlidingUpPanelLayout) findViewById(R.id.sliding_layout));
        cartManager.hidePanel();
        registerListener(cartManager);
        addCartFragment();

        spinnerItems.add(new Item("stay", 0) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        spinnerItems.add(new Item("rent bike/car", 0) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        spinnerItems.add(new Item("activities", 0) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        spinnerItems.add(new Item("transport", 0) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.productSpinner);
        final ItemSpinnerAdapter adapter = new ItemSpinnerAdapter(this, spinnerItems);
        spinner.setAdapter(adapter);

    }

    private void addCartFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.cartContainer, new ProductOrderFragment()).commit();
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
        switch (item.getItemId()) {
            case R.id.action_filter:
                EventBus.getDefault().post(new ChangeContentEvent(ProductFilter.class));
                break;
            case R.id.action_maps:
                if (mapsIcon == false) {
                    replaceContent(new Fragment());
                    mapsIcon = true;
                } else {
                    replaceContent(getListFragment());
                    mapsIcon = false;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class DialogProductSort extends DialogFragment {
        private View view;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            view = inflater.inflate(R.layout.dialog_product_sort, null);
            final CheckBox popularity = (CheckBox) view.findViewById(R.id.cb_popularity);
            final CheckBox lowToHigh = (CheckBox) view.findViewById(R.id.cb_low_to_high);
            final CheckBox highToLow = (CheckBox) view.findViewById(R.id.cb_high_to_low);
            final CheckBox discount = (CheckBox) view.findViewById(R.id.cb_discount);

            if (popularity.isChecked()) {
                popularity.setChecked(false);
                dismiss();
            }
            if (lowToHigh.isChecked()) {
                lowToHigh.setChecked(false);
                dismiss();
            }
            if (highToLow.isChecked()) {
                highToLow.setChecked(false);
                dismiss();
            }
            if (discount.isChecked()) {
                discount.setChecked(false);
                dismiss();
            }
            return view;
        }

        public void onRadioButtonClicked(View view) {
            // Is the button now checked?
            boolean checked = ((RadioButton) view).isChecked();

            // Check which radio button was clicked
            switch (view.getId()) {
                case R.id.rb_low_to_high:
                    if (checked) {
                        Toast.makeText(getActivity(), "product booked", Toast.LENGTH_LONG).show();
                        dismiss();
                    }
                    // Pirates are the best
                    break;
                case R.id.rb_discount:
                    if (checked)
                        Toast.makeText(getActivity(), "product booked", Toast.LENGTH_LONG).show();
                    // Ninjas rule
                    break;
                case R.id.rb_high_to_low:
                    if (checked)
                        Toast.makeText(getActivity(), "product booked", Toast.LENGTH_LONG).show();
                    // Ninjas rule
                    break;
                case R.id.rb_popularity:
                    if (checked)
                        Toast.makeText(getActivity(), "product booked", Toast.LENGTH_LONG).show();
                    // Ninjas rule
                    break;
            }
        }
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
        //ProductMapFragment frag = new ProductMapFragment();
//        productAdapter.addProduct(new Product());
        //addArguments(frag);
        return null;
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

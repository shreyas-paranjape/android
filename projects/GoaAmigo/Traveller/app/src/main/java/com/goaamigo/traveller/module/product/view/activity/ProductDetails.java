package com.goaamigo.traveller.module.product.view.activity;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.fragment.ProductOrderFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.order.CartManager;
import com.order.CurrentOrderManager;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.util.LocationUtil;
import com.view.activity.AbstractActivity;

import java.util.HashMap;

import de.greenrobot.event.EventBus;
import model.catalogue.Product;

public class ProductDetails extends AbstractActivity implements OnMapReadyCallback {
    private SliderLayout mDemoSlider;
    private TextView productAddress, productName, productPrice, termsCond;
    private String terms;
    private Product product;
    private CartManager cartManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        product = (Product) b.getSerializable("PRODUCT");
        cartManager = new CartManager((SlidingUpPanelLayout) findViewById(R.id.sliding_layout));
        cartManager.hidePanel();
        registerListener(cartManager);
        addCartFragment();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        productName = (TextView) findViewById(R.id.detailFragName);
        productAddress = (TextView) findViewById(R.id.detailFragAdrress);
        productPrice = (TextView) findViewById(R.id.tv_product_price);
        termsCond = (TextView) findViewById(R.id.tv_terms);
        setTitle(product.getName());
        setLayout();
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

    private void setLayout() {

        productAddress.setText(product.getProductLocation());
        productPrice.setText("Rs " + product.getProductPrice());
        terms = getString(R.string.terms);
        ExpandableTextView description = (ExpandableTextView) findViewById(R.id.expand_text_view);
        description.setText(getString(R.string.terms));
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("image 1", product.getProductImage());
        file_maps.put("image 2", product.getProductImage());
        file_maps.put("image 3", product.getProductImage());

        termsCond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                TermsAndConditions Dialog = new TermsAndConditions();
                Dialog.show(manager, "My Dialog");
            }
        });
        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image(file_maps.get(name))
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            Toast.makeText(
                                    ProductDetails.this, "Image should open up in full screen", Toast.LENGTH_LONG).show();
                        }
                    })
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_detail_menu, menu);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng goa;
        Location location = LocationUtil.getLastKnownLocation(
                (LocationManager) getSystemService(Context.LOCATION_SERVICE));

        goa = new LatLng(location.getLatitude(), location.getLongitude());

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(goa));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(14);
        googleMap.animateCamera(zoom);
        googleMap.addMarker(new MarkerOptions()
                        .position(goa)
                        .title(product.getName())
        );
    }

    public class TermsAndConditions extends DialogFragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            View view = inflater.inflate(R.layout.dialog_terms_and_conditions, null);
            Button ok = (Button) view.findViewById(R.id.btn_terms_and_conditions);
            TextView textTerms = (TextView) view.findViewById(R.id.tv_terms_and_conditions);
            textTerms.setText(terms);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            return view;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_book_product:
                //Toast.makeText(this, "product booked", Toast.LENGTH_LONG).show();
                addItem(product);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addItem(Product product) {
        modifyItem(product, CurrentOrderManager.CartAction.ADD);
    }

    private void modifyItem(Product product, CurrentOrderManager.CartAction action) {
        EventBus.getDefault().post(
                new CurrentOrderManager.ModifyCartEvent(product, action));
    }

    protected int getLayoutId() {
        return R.layout.activity_product_details;
    }

    protected int getToolbarId() {
        return R.id.toolbar;
    }
}

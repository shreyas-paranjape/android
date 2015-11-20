package com.goaamigo.traveller.module.product.view.activity;

import android.app.DialogFragment;
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
import com.google.android.gms.maps.model.MarkerOptions;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.order.CartManager;
import com.order.CurrentOrderManager;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.view.activity.AbstractActivity;

import java.util.HashMap;

import de.greenrobot.event.EventBus;
import model.catalogue.Product;

public class ProductDetails extends AbstractActivity implements OnMapReadyCallback {
    private SliderLayout mDemoSlider;
    private TextView address, name, productPrice, termsCond;
    private String terms;
    private Product product;
    private CartManager cartManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartManager = new CartManager((SlidingUpPanelLayout) findViewById(R.id.sliding_layout));
        cartManager.hidePanel();
        registerListener(cartManager);
        addCartFragment();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);
        Bundle b = getIntent().getExtras();
        product = (Product) b.getSerializable("PRODUCT");
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
        setTitle(product.getName());
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        name = (TextView) findViewById(R.id.detailFragName);
        address = (TextView) findViewById(R.id.detailFragAdrress);
        productPrice = (TextView) findViewById(R.id.tv_product_price);
        termsCond = (TextView) findViewById(R.id.tv_terms);
        address.setText(product.getProductLocation());
        productPrice.setText("Rs " + product.getProductPrice());

        terms = "The terms and conditions document includes the following provisions:\n" +
                "\n" +
                "    a licence of the copyright in the website (and restrictions on what may be done with the material on the website);\n" +
                "    a disclaimer of liability;\n" +
                "    a clause governing the use of passwords and restricted areas of the website;\n" +
                "    an acceptable use clause;\n" +
                "    a variation clause;\n" +
                "    an entire agreement clause;\n" +
                "    a clause specifying the applicable law and the jurisdiction in which disputes will be decided; and\n" +
                "    a provision specifying some of the information which needs to be disclosed under the Ecommerce Regulations.";

        ExpandableTextView description = (ExpandableTextView) findViewById(R.id.expand_text_view);
        description.setText("2.3 km from Kurla Railway Station, 8 km from Mount Mary Church.\n" +
                "\n" +
                "Trident Bandra Kurla is located in Bandra Kurla Complex, which is dotted with numerous commercial, business and entertainment centres. Bandra Lake and Gateway of India are located within a short distance from the hotel. There are 412 rooms and 24 suites, which are spacious and well furnished. Rooms ensure abundant natural light and feature High-speed Internet, electronic safe, LCD TV, iPod dock and mini bar.\n" +
                "\n" +
                "Ideal for organising business events and parties, this 5 star hotel features three spacious and well-decorated banquet halls. A spa, saloon, fitness centre, reference library and an outdoor pool are other highlights of the hotel.\n" +
                "\n" +
                "O22 offering All Day Dining, Botticino- the Italian speciality restaurant and Maya, specialising in authentic Indian delicacies are the on-site dining options. Relish delicious chocolates, breads, cookies, pastries and others at Trident Patisserie & Delicatessen- the in-house bakery.\n" +
                "\n" +
                "The hotel has a boutique at the lobby level called Sandouk that sells jewelry items, saris, leather merchandise and spa products. The hotel now offers Trident Mini, a complimentary drop service in a Mini Cooper to nearby areas");

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
                            Toast.makeText(ProductDetails.this, "Image should open up in full screen", Toast.LENGTH_LONG).show();
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
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(product.getPosition()));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(14);
        googleMap.animateCamera(zoom);
        googleMap.addMarker(new MarkerOptions()
                        .position(product.getPosition())
                        .title(product.getName())
        );
    }

    public class TermsAndConditions extends DialogFragment {

        TextView textTerms;
        Button ok;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            View view = inflater.inflate(R.layout.terms_and_conditions_dialog, null);
            ok = (Button) view.findViewById(R.id.btn_terms_and_conditions);
            textTerms = (TextView) view.findViewById(R.id.tv_terms_and_conditions);
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

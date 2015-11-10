package com.goaamigo.traveller.module.product.view.fragment;

import android.os.Bundle;

import com.goaamigo.model.trip.catalogue.Product;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.view.component.GoaMapActivity;
import com.goaamigo.traveller.module.product.view.adapter.ProductAdapter;

import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.MapPosition;

import java.util.List;

import de.greenrobot.event.EventBus;
import map.util.Util;
import map.view.component.MapFragment;

public class ProductMapFragment extends MapFragment {

    private static final String TAG = GoaMapActivity.class.getName();
    public static final LatLong LATLONG_PANAJI = new LatLong(15.4989, 73.8278);

    private ProductAdapter productAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productAdapter = (ProductAdapter) getArguments()
                .getSerializable("PRODUCT_ADAPTER");
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_map;
    }

    @Override
    protected int getMapViewId() {
        return R.id.mapView;
    }

    @Override
    protected String getMapFileName() {
        return "maps/goa.map";
    }


    @Override
    protected MapPosition getDefaultInitialPosition() {
        return new MapPosition(LATLONG_PANAJI, (byte) 12);
    }

    public void onEvent(ProductAdapter.ProductDataSetChanged event) {
        refreshLayers();
    }

    private void refreshLayers() {
        mapView.getLayerManager().getLayers().clear();
        addProductsLayer();
    }

    private void addProductsLayer() {
        List<Product> products = productAdapter.getProducts();
        for (Product product : products) {
            addBalloonForProduct(product);
        }
    }

    private void addBalloonForProduct(Product product) {
        Util.addBalloonMarker(
                getActivity(),
                mapView,
                LATLONG_PANAJI,
                product.getName(),
                R.mipmap.balloon_overlay_unfocused);
    }

}

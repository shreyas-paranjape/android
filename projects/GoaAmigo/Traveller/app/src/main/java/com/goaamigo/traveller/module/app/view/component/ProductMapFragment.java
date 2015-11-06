package com.goaamigo.traveller.module.app.view.component;

import com.goaamigo.traveller.R;

import map.view.component.MapFragment;

public class ProductMapFragment extends MapFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_map;
    }

    @Override
    protected int getMapViewId() {
        return R.id.mapView;
    }

    @Override
    protected String getMapFileName() {
        return "maps/goa.map";
    }
}

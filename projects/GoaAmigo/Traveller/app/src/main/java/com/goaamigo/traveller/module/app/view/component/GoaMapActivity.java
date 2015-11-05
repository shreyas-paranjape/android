package com.goaamigo.traveller.module.app.view.component;

import android.os.Bundle;

import com.goaamigo.traveller.R;

import org.mapsforge.core.model.LatLong;
import org.mapsforge.core.model.MapPosition;
import org.mapsforge.map.android.graphics.AndroidGraphicFactory;

import java.util.ArrayList;
import java.util.List;

import map.util.Util;
import map.view.component.MapActivity;

public class GoaMapActivity extends MapActivity {

    public static final LatLong LATLONG_PANAJI = new LatLong(15.4989, 73.8278);
    private static final String TAG = GoaMapActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AndroidGraphicFactory.INSTANCE == null) {
            AndroidGraphicFactory.createInstance(getApplication());
        }
        super.onCreate(savedInstanceState);
    }

    protected int getLayoutId() {
        return R.layout.activity_map;
    }

    protected int getMapViewId() {
        return R.id.mapView;
    }

    protected String getMapFileName() {
        return "maps/goa.map";
    }

    protected void createLayers() {
        super.createLayers();


        // Add a balloon
        Util.addBalloonMarker(
                this,
                mapView,
                LATLONG_PANAJI,
                "Panaji",
                R.mipmap.balloon_overlay_unfocused);

        // Add a line
        List<LatLong> points = new ArrayList<>();
        points.add(LATLONG_PANAJI);
        points.add(new LatLong(LATLONG_PANAJI.latitude + 0.1, LATLONG_PANAJI.longitude + 0.1));
        Util.addLine(mapView, points);
    }

    @Override
    protected MapPosition getDefaultInitialPosition() {
        return new MapPosition(LATLONG_PANAJI, (byte) 12);
    }
}

package com.goaamigo.traveller.module.app.view.component;

import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.goaamigo.traveller.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.util.LocationUtil;
import com.view.activity.AbstractActivity;

public class MapsActivity extends AbstractActivity implements OnMapReadyCallback {

    LatLng myPosition = new LatLng(0,0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);
        googleMap.setMyLocationEnabled(true);
        googleMap.animateCamera(zoom);
        LatLng goa;
        Location location = LocationUtil.getLastKnownLocation(
                (LocationManager) getSystemService(Context.LOCATION_SERVICE));
        Log.i("Location test", String.valueOf(location));
        if(location!= null){
            goa = new LatLng(location.getLatitude(),location.getLongitude());

        }else{
            goa = new LatLng(0,0);
        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(goa));
        Marker marker = googleMap.addMarker(new MarkerOptions()
                .position(goa)
                .draggable(true));
        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                myPosition = marker.getPosition();
            }
        });
        Log.i("marker position", String.valueOf(myPosition));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_save) {
            Log.i("marker position", String.valueOf(myPosition));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_maps;
    }

    @Override
    protected int getDrawerFragmentId() {
        return 0;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected Fragment getInitContent() {
        return null;
    }

    @Override
    protected int getContentContainerId() {
        return 0;
    }

    @Override
    protected DrawerLayout getDrawerLayout() {
        return null;
    }


}

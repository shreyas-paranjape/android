package com.goaamigo.traveller.module.app.view.component;

import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.goaamigo.traveller.module.MapData.Hotels;
import com.goaamigo.traveller.module.MapData.MarkerEntity;
import com.goaamigo.traveller.module.MapData.Restaurants;
import com.goaamigo.traveller.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.util.LocationUtil;
import com.view.activity.AbstractActivity;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AbstractActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private List<MarkerEntity> Hoteldata;
    private List<MarkerEntity> ATMdata;
    private List<MarkerEntity> restaurantData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Hoteldata = new ArrayList<>();
        ATMdata = new ArrayList<>();
        restaurantData = new ArrayList<>();

        Hotels HData = new Hotels();
        HData.setTitle("Vivanta by taj");
        HData.setLatitude(15.493904);
        HData.setLongitude(73.820391);
        Hoteldata.add(HData);

        HData = new Hotels();
        HData.setTitle("Hotel Goa International");
        HData.setLatitude(15.480503);
        HData.setLongitude(73.811882);
        Hoteldata.add(HData);

        HData = new Hotels();
        HData.setTitle("Hotel Solmar");
        HData.setLatitude(15.485033);
        HData.setLongitude(73.809968);
        Hoteldata.add(HData);

        HData = new Hotels();
        HData.setTitle("Goa Marriott Resort & Spa");
        HData.setLatitude(15.486575);
        HData.setLongitude(73.809279);
        Hoteldata.add(HData);

        HData = new Hotels();
        HData.setTitle("Hotel Magsons Center, Cafe Coffee Day");
        HData.setLatitude(15.482258);
        HData.setLongitude(73.809036);
        Hoteldata.add(HData);

        Restaurants RData = new Restaurants();
        RData.setTitle("Hotel Magsons Center, Cafe Coffee Day");
        RData.setLatitude(15.482255);
        RData.setLongitude(73.809047);
        restaurantData.add(RData);

        RData = new Restaurants();
        RData.setTitle("Food Land");
        RData.setLatitude(15.483761);
        RData.setLongitude(73.809194);
        restaurantData.add(RData);

        RData = new Restaurants();
        RData.setTitle("Rio Restaurant\n");
        RData.setLatitude(15.484812);
        RData.setLongitude(73.821633);
        restaurantData.add(RData);

        Button hotel = (Button) findViewById(R.id.hotel);
        Button rest = (Button) findViewById(R.id.rest);
        Button atm = (Button) findViewById(R.id.atm);
        Button hospital = (Button) findViewById(R.id.hospital);
        Button carwash = (Button) findViewById(R.id.carWash);
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh(Hoteldata, R.drawable.ic_accessibility_black_24dp);
            }
        });
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh(restaurantData, R.drawable.ic_account_box);
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        refresh(Hoteldata, R.drawable.ic_home_black_24dp);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void refresh(List<MarkerEntity> markers, int imageB) {
        googleMap.clear();
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setMyLocationEnabled(true);
        LatLng goa;
        Location location = LocationUtil.getLastKnownLocation(
                (LocationManager) getSystemService(Context.LOCATION_SERVICE));

        goa = new LatLng(location.getLatitude(),location.getLongitude());

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(goa));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(14);
        googleMap.animateCamera(zoom);

        for (MarkerEntity markerEntity : markers) {
            googleMap.addMarker(new MarkerOptions()
                            .position(markerEntity.getPosition())
                            .title(markerEntity.getTitle())
                            .icon(BitmapDescriptorFactory.fromResource(imageB))

            );
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

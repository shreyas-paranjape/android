package com.goaamigo.traveller.module.app.view.component;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.app.SearchManager;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.singleton.Constant;
import com.goaamigo.traveller.module.app.view.adapter.MenuRVAdapter;
import com.goaamigo.traveller.module.app.view.adapter.SearchableActivity;
import com.goaamigo.traveller.module.trip.view.component.TripActivity;
import com.view.activity.AbstractActivity;

import java.io.Serializable;

public class HomeActivity extends AbstractActivity {

    private static final String TAG = HomeActivity.class.getName();
    private EventListener eventListener = new EventListener();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerListener(eventListener);
    }

    @Override
    protected void onDestroy() {
        unRegisterListener(eventListener);
        super.onDestroy();
    }

    protected int getLayoutId() {
        return R.layout.app_home;
    }

    protected int getDrawerFragmentId() {
        return R.id.drawer;
    }

    protected int getToolbarId() {
        return R.id.toolbar;
    }

    protected int getContentContainerId() {
        return R.id.container;
    }

    protected Fragment getInitContent() {
        return new MenuFragment();
    }

    protected DrawerLayout getDrawerLayout() {
        return (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_searchable, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_search){
            onSearchRequested();
        }
        return super.onOptionsItemSelected(item);
    }

    public class EventListener {
        public void onEvent(MenuRVAdapter.MenuButtonClicked event) {
            Log.i(TAG, "in on event");
            startTripActivity(event);
        }

    }

    public interface MenuButtonEvent extends Serializable {
        Fragment getFragment();
    }


    private void startTripActivity(Serializable event) {
        Intent intent = new Intent(this, TripActivity.class);
        intent.putExtra(Constant.EVENT, event);
        startActivity(intent);
    }
}

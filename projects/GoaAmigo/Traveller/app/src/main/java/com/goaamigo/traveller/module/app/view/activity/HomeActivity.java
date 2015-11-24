package com.goaamigo.traveller.module.app.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.view.component.MenuFragment;
import com.goaamigo.traveller.module.app.view.component.SearchableActivity;
import com.goaamigo.traveller.module.app.view.component.SearchableFragment;
import com.view.activity.AbstractActivity;

public class HomeActivity extends AbstractActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    SearchableFragment searchableFragment;
    //private static final String TAG = HomeActivity.class.getName();
    //private EventListener eventListener = new EventListener();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //registerListener(eventListener);
        /*fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        searchableFragment = new SearchableFragment();
        fragmentTransaction.commit();*/
    }

    @Override
    protected void onDestroy() {
        //unRegisterListener(eventListener);
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
        } else if (id == R.id.action_search) {
            startNewActivity(SearchableActivity.class, new Bundle());
           /* onSearchRequested();
            fragmentTransaction.replace(R.id.container, searchableFragment);
            fragmentTransaction.commit();*/
        }
        return super.onOptionsItemSelected(item);
    }

    //public class EventListener {
    //}

}

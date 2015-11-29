package com.goaamigo.traveller.module.app.view.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.view.fragment.MenuFragment;
import com.view.activity.AbstractActivity;

public class HomeActivity extends AbstractActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                startNewActivity(SearchableActivity.class);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

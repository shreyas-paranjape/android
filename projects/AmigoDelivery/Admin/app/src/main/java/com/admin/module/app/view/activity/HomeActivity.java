package com.admin.module.app.view.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.admin.R;
import com.admin.module.app.infra.socket.SocketConnectionManager;
import com.admin.module.catalogue.view.component.list.ProductListFragment;
import com.admin.module.party.view.activity.AuthActivity;
import com.view.activity.AbstractActivity;

public class HomeActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isLoggedIn()) {
            startService(new Intent(this, SocketConnectionManager.class));
        } else {
            requestAuth(AuthActivity.class);
        }
    }

    @Override
    protected void onLoginSuccess(Intent returnedData) {
        super.onLoginSuccess(returnedData);
    }

    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    protected int getDrawerFragmentId() {
        return R.id.navigation_drawer;
    }

    protected int getToolbarId() {
        return R.id.toolbar;
    }

    protected Fragment getInitContent() {
        return new ProductListFragment();
    }

    protected int getContentContainerId() {
        return R.id.container;
    }

    protected DrawerLayout getDrawerLayout() {
        return (DrawerLayout) findViewById(R.id.drawer_layout);
    }
}



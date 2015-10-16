package com.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.view.fragment.NavigationDrawer;

import de.greenrobot.event.EventBus;

public abstract class AbstractActivity extends AppCompatActivity {

    private EventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initListeners();
        setupDrawer();
        setupToolbar();
        replaceContent(getInitContent());
    }

    @Override
    protected void onDestroy() {
        unRegisterListener(listener);
        super.onDestroy();
    }

    protected void replaceContent(Fragment newFragment) {
        int containerId = getContentContainerId();
        if (containerId != 0) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(containerId, newFragment).commit();
        }
    }

    protected void setupDrawer() {
        int drawerId = getDrawerFragmentId();
        if (drawerId != 0) {
            EventBus.getDefault().post(
                    new NavigationDrawer.SetupDrawerEvent(
                            getDrawerLayout(),
                            drawerId));
        }

    }

    protected void setupToolbar() {
        int toolBarId = getToolbarId();
        if (toolBarId != 0) {
            Toolbar toolbar = (Toolbar) findViewById(toolBarId);
            if (toolbar != null) {
                toolbar.setTitleTextColor(Color.BLACK);
                setSupportActionBar(toolbar);
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setHomeButtonEnabled(true);
                }
            }
        }
    }

    protected void unRegisterListener(Object listener) {
        if (EventBus.getDefault().isRegistered(listener)) {
            EventBus.getDefault().unregister(listener);
        }
    }

    protected void registerListener(Object listener) {
        if (!EventBus.getDefault().isRegistered(listener)) {
            EventBus.getDefault().register(listener);
        }
    }

    private class EventListener {
        public void onEvent(NavigationDrawer.DrawerItemClickedEvent event) {
            replaceContent(event.getItem().getDisplayFragment());
        }
    }

    private void initListeners() {
        listener = new EventListener();
        registerListener(listener);
    }

    protected abstract int getLayoutId();

    protected abstract int getDrawerFragmentId();

    protected abstract int getToolbarId();

    protected abstract Fragment getInitContent();

    protected abstract int getContentContainerId();

    protected abstract DrawerLayout getDrawerLayout();

}

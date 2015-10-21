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

    protected final EventBus eventBus = EventBus.getDefault();
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
        if (containerId != 0 && newFragment != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(containerId, newFragment, newFragment.getClass().getName())
                    .addToBackStack(newFragment.getClass().getName())
                    .commit();
        }
    }

    protected void setupDrawer() {
        int drawerId = getDrawerFragmentId();
        if (drawerId != 0) {
            eventBus.post(
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
        if (eventBus.isRegistered(listener)) {
            eventBus.unregister(listener);
        }
    }

    protected void registerListener(Object listener) {
        if (!eventBus.isRegistered(listener)) {
            eventBus.register(listener);
        }
    }

    private class EventListener {
        public void onEvent(NavigationDrawer.DrawerItemClickedEvent event) {
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
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

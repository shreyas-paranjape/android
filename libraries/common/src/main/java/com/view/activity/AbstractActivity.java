package com.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.event.ChangeContentEvent;
import com.view.fragment.NavigationDrawer;

import de.greenrobot.event.EventBus;

public abstract class AbstractActivity extends AppCompatActivity {

    protected final EventBus eventBus = EventBus.getDefault();
    private final EventListener listener;
    private TextView tvTitle;

    protected AbstractActivity() {
        listener = new EventListener();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        registerListener(listener);
        setupDrawer();
        setupToolbar();
        replaceContent(getInitContent());
        setTitle("");
    }

    @Override
    public void setTitle(CharSequence title) {
        if (tvTitle != null) {
            super.setTitle("");
            tvTitle.setText(title);
        } else {
            super.setTitle(title);
        }
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
                    .commit();
        }
    }

    protected void replaceContent(Fragment newFragment, boolean addToBackStack) {
        int containerId = getContentContainerId();
        if (containerId != 0 && newFragment != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(containerId, newFragment, newFragment.getClass().getName())
                    .addToBackStack(newFragment.getClass().getName())
                    .commit();
        }
    }

    protected void startNewActivity(Class clazz, Bundle data) {
        Intent intent = new Intent(AbstractActivity.this, clazz);
        intent.putExtras(data);
        startActivity(intent);
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
                if (getTitleId() != 0) {
                    tvTitle = (TextView) toolbar.findViewById(getTitleId());
                }
                setSupportActionBar(toolbar);
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setHomeButtonEnabled(true);
                }
            }
        }
    }

    protected void unRegisterListener(Object... listeners) {
        for (Object listener : listeners) {
            if (eventBus.isRegistered(listener)) {
                eventBus.unregister(listener);
            }
        }
    }

    protected void registerListener(Object... listeners) {
        for (Object listener : listeners) {
            if (!eventBus.isRegistered(listener)) {
                eventBus.register(listener);
            }
        }
    }

    private class EventListener {
        public void onEvent(NavigationDrawer.DrawerItemClickedEvent event) {
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            replaceContent(event.getItem().getDisplayFragment());
        }

        public void onEvent(ChangeContentEvent event) {
            switch (event.getNewContent()) {
                case ACTIVITY:
                    startNewActivity(event.getContentClass(), event.getData());
                    break;
                case FRAGMENT:
                    try {
                        Fragment frag = (Fragment) event.getContentClass().newInstance();
                        frag.setArguments(event.getData());
                        replaceContent(frag);
                    } catch (Exception e) {
                        // Do nothing
                    }
                    break;
            }
        }
    }

    protected int getLayoutId() {
        return 0;
    }

    protected int getDrawerFragmentId() {
        return 0;
    }

    protected int getToolbarId() {
        return 0;
    }

    protected Fragment getInitContent() {
        return null;
    }

    protected int getContentContainerId() {
        return 0;
    }

    protected DrawerLayout getDrawerLayout() {
        return null;
    }

    protected int getTitleId() {
        return 0;
    }

}

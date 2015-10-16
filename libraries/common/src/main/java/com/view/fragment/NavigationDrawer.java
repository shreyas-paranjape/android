package com.view.fragment;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.R;
import com.view.model.Item;

import de.greenrobot.event.EventBus;

/**
 * Attach this to any activity with a drawer layout.
 * Post a SetupDrawerEvent with the fragmentId and DrawerLayoutId when
 * you need to attach this fragment.
 * Override onCreateView to create the drawer view. Initialize all dynamic content in initView
 **/
public abstract class NavigationDrawer extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;
    private View root;

    public NavigationDrawer() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(getLayoutId(), container, false);
        return root;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item)
                || super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(SetupDrawerEvent event) {
        if (getActivity() != null) {
            mFragmentContainerView = getActivity().findViewById(event.getFragmentId());
            mDrawerLayout = event.getDrawerLayout();
            mDrawerToggle = getActionBarDrawerToggle();
            mDrawerLayout.post(new Runnable() {
                @Override
                public void run() {
                    mDrawerToggle.syncState();
                }
            });
            mDrawerLayout.setDrawerListener(mDrawerToggle);
            initView(root);
        }
    }

    protected abstract void initView(View v);

    protected abstract int getLayoutId();

    protected void drawerItemClicked(Item item) {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        EventBus.getDefault().post(new DrawerItemClickedEvent(item));
    }

    private ActionBarDrawerToggle getActionBarDrawerToggle() {
        return new ActionBarDrawerToggle(
                getActivity(),
                mDrawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }
                getActivity().invalidateOptionsMenu();
            }
        };
    }

    public class DrawerItemClickedEvent {
        private final Item item;

        public DrawerItemClickedEvent(Item item) {
            this.item = item;
        }

        public Item getItem() {
            return item;
        }
    }

    public static class SetupDrawerEvent {

        private final DrawerLayout drawerLayout;
        private final int fragmentId;

        public SetupDrawerEvent(DrawerLayout drawerLayout, int fragmentId) {
            this.drawerLayout = drawerLayout;
            this.fragmentId = fragmentId;
        }

        public DrawerLayout getDrawerLayout() {
            return drawerLayout;
        }

        public int getFragmentId() {
            return fragmentId;
        }

    }

}

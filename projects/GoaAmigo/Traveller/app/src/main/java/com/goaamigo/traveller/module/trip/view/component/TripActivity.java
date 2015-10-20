package com.goaamigo.traveller.module.trip.view.component;

import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.goaamigo.model.MenuData;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.singleton.Constant;
import com.goaamigo.traveller.module.app.view.adapter.MenuRVAdapter;
import com.view.activity.AbstractActivity;

import java.util.HashMap;
import java.util.Map;

public class TripActivity extends AbstractActivity {


    private static final String TAG = TripActivity.class.getName();
    private EventListener listener = new EventListener();
    private Map<MenuData, Fragment> menuFragMap = new HashMap<>();

    public TripActivity() {
        menuFragMap.put(new MenuData("Home"),new SearchTripFragment());
        menuFragMap.put(new MenuData("login to trip"),new LoginFragment());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerListener(listener);
    }

    @Override
    protected void onDestroy() {
        unRegisterListener(listener);
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_trip;
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
        MenuData menuItem = ((MenuRVAdapter.MenuButtonClicked)getIntent()
                .getSerializableExtra(Constant.EVENT)).getMenuData();
        Fragment frag = menuFragMap.get(menuItem);
       return frag;
    }

    @Override
    protected int getContentContainerId() {
        return R.id.container;
    }

    @Override
    protected DrawerLayout getDrawerLayout() {
        return null;
    }

    public class EventListener {

        public void onEvent(SearchTripFragment.FindTripButtonClickEvent event) {
            replaceContent(new TripResultsFragment());
        }
        public  void onEvent(LoginFragment.FindLoginButtonClickEvent event){
            replaceContent(new TripResultsFragment());
        }
        public void onEvent(LoginFragment.FindSignUpButtonClickEvent event){
            replaceContent(new SignUpFragment());
        }
    }
}

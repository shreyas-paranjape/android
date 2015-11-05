package com.goaamigo.traveller.module.trip.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.view.component.DrawerFragment;
import com.goaamigo.traveller.module.app.view.component.ProfileFragment;
import com.goaamigo.traveller.module.app.view.model.MenuData;
import com.goaamigo.traveller.module.deal.view.component.component.FindDealsFragment;
import com.view.activity.AbstractActivity;

import java.util.HashMap;
import java.util.Map;

public class TripActivity extends AbstractActivity {


    private static final String TAG = TripActivity.class.getName();
    private EventListener listener = new EventListener();
    private Map<MenuData, Fragment> menuFragMap = new HashMap<>();

    public TripActivity() {
        menuFragMap.put(new MenuData("Search Trip"),new SearchTripFragment());
        menuFragMap.put(new MenuData("login to trip"),new LoginFragment());
        menuFragMap.put(new MenuData("Deals"),new FindDealsFragment());
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
        MenuData menuItem = (MenuData)getIntent().getExtras().get("MENUDATA");
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
            replaceContent(new TripFragment());
        }
        public void onEvent(LoginFragment.FindSignUpButtonClickEvent event){
            replaceContent(new SignUpFragment());
        }

    }
}

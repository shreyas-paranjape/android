package com.goaamigo.traveller.module.trip.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.app.singleton.Constant;
import com.goaamigo.traveller.module.app.view.component.HomeActivity;
import com.view.activity.AbstractActivity;

public class TripActivity extends AbstractActivity {

    private EventListener listener = new EventListener();

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
       return ((HomeActivity.MenuButtonEvent)getIntent().getSerializableExtra(Constant.EVENT)).getFragment();
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

    }
}

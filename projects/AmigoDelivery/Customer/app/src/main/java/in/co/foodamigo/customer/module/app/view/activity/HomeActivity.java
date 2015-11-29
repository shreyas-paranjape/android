package in.co.foodamigo.customer.module.app.view.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.view.activity.AbstractActivity;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.catalogue.view.component.MenuFragment;
import in.co.foodamigo.customer.module.order.view.fragment.FoodOrderFragment;

public class HomeActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCart(R.id.sliding_layout, R.id.cartContainer, new FoodOrderFragment());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected int getLayoutId() {
        return R.layout.activity_home;
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

    @Override
    protected int getTitleId() {
        return R.id.tvTitle;
    }

    protected DrawerLayout getDrawerLayout() {
        return (DrawerLayout) findViewById(R.id.drawer_layout);
    }
}

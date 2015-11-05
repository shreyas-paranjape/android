package in.co.foodamigo.customer.module.app.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.view.activity.AbstractActivity;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.catalogue.view.component.MenuFragment;
import in.co.foodamigo.customer.module.order.controller.CartManager;
import in.co.foodamigo.customer.module.order.view.component.OrderFragment;

public class HomeActivity extends AbstractActivity {

    private CartManager cartManager;
    private final EventListener listener = new EventListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartManager = new CartManager((SlidingUpPanelLayout) findViewById(R.id.sliding_layout));
        cartManager.hidePanel();
        registerListener(cartManager);
        addCartFragment();
    }


    @Override
    public void onBackPressed() {
        if (cartManager.isCartExpanded()) {
            cartManager.collapsePanel();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        unRegisterListener(listener, cartManager);
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

    private void addCartFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.cartContainer, new OrderFragment()).commit();
    }

    private class EventListener {
    }
}

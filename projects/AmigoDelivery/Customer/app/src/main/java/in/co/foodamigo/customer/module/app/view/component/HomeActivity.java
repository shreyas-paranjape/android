package in.co.foodamigo.customer.module.app.view.component;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.view.activity.AbstractActivity;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.app.view.event.ChangeContentEvent;
import in.co.foodamigo.customer.module.catalogue.view.component.MenuFragment;
import in.co.foodamigo.customer.module.order.controller.CartManager;
import in.co.foodamigo.customer.module.order.view.component.OrderFragment;

public class HomeActivity extends AbstractActivity {

    private CartManager cartManager;
    private EventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("");
        listener = new EventListener();
        cartManager = new CartManager((SlidingUpPanelLayout) findViewById(R.id.sliding_layout));
        registerListener(cartManager, listener);
        addCartFragment();
    }

    @Override
    public void onBackPressed() {
        if (cartManager.isCartExpanded()) {
            cartManager.collapseCartPanel();
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

    protected DrawerLayout getDrawerLayout() {
        return (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    private void addCartFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.cartContainer, new OrderFragment()).commit();
    }

    private class EventListener {

        public void onEvent(ChangeContentEvent event) {
            switch (event.getNewContent()) {
                case ACTIVITY:
                    Intent intent = new Intent(HomeActivity.this, event.getContentClass());
                    intent.putExtras(event.getData());
                    startActivity(intent);
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
}

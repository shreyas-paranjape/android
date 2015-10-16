package in.co.foodamigo.customer.module.app.view.component;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.view.activity.AbstractActivity;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.catalogue.view.component.MenuFragment;
import in.co.foodamigo.customer.module.order.controller.CartManager;
import in.co.foodamigo.customer.module.order.view.component.OrderFragment;
import in.co.foodamigo.customer.module.profile.view.component.ProfileFragment;

public class HomeActivity extends AbstractActivity {

    private CartManager cartManager;
    private Menu menu;
    //private EventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initListeners();
        initCart();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        this.menu = menu;
        menu.findItem(R.id.item_menu).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_profile:
                showProfileView();
                return true;
            case R.id.item_menu:
                showMenuView();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
        //unRegisterListener(listener);
        unRegisterListener(cartManager);
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        showProfileView();
    }

    protected int getLayoutId() {
        return R.layout.app_home;
    }

    protected int getDrawerFragmentId() {
//        return 0;
        return R.id.drawer;
    }

    protected int getToolbarId() {
//        return 0;
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

    // PRIVATE
    private void showProfileView() {
        menu.findItem(R.id.item_profile).setVisible(false);
        menu.findItem(R.id.item_menu).setVisible(true);
        replaceContent(new ProfileFragment());
    }

    private void showMenuView() {
        menu.findItem(R.id.item_profile).setVisible(true);
        menu.findItem(R.id.item_menu).setVisible(false);
        replaceContent(new MenuFragment());
    }

    private void initView() {
        setTitle("");
        addCartFragment();
    }

    private void initCart() {
        cartManager = new CartManager((SlidingUpPanelLayout) findViewById(R.id.sliding_layout));
        registerListener(cartManager);
    }

//    private void initListeners() {
//        listener = new EventListener();
//        registerListener(listener);
//    }

    private void addCartFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.cartContainer, new OrderFragment()).commit();
    }

    private class EventListener {
    }
}

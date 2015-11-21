package restaurant.order.module.app.view.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.Window;
import android.view.WindowManager;

import com.order.CartManager;
import com.orm.SugarRecord;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.util.Constant;
import com.util.Util;
import com.view.activity.AbstractActivity;

import model.catalogue.ProductCategory;
import restaurant.order.R;
import restaurant.order.module.catalogue.view.component.MenuFragment;
import restaurant.order.module.order.view.component.FoodOrderFragment;

public class HomeActivity extends AbstractActivity {

    private CartManager cartManager;
    //private final EventListener listener = new EventListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        cartManager = new CartManager((SlidingUpPanelLayout) findViewById(R.id.sliding_layout));
        cartManager.hidePanel();
        initTabs();
        registerListener(cartManager);
        addCartFragment();
        replaceContent(MenuFragment.class,
                Util.bundleSerializable(
                        Constant.PRODUCT_CATEGORY,
                        SugarRecord.findById(ProductCategory.class, 1)));
    }

    private void initTabs() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Food"));
        tabLayout.addTab(tabLayout.newTab().setText("Drinks"));
        tabLayout.addTab(tabLayout.newTab().setText("Dessert"));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                replaceContent(MenuFragment.class,
                        Util.bundleSerializable(
                                Constant.PRODUCT_CATEGORY,
                                SugarRecord.findById(ProductCategory.class, tab.getPosition() + 1)));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
        //unRegisterListener(listener, cartManager);
        super.onDestroy();
    }

    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    protected int getDrawerFragmentId() {
        return R.id.drawer;
    }

    protected int getContentContainerId() {
        return R.id.container;
    }

    private void addCartFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.cartContainer, new FoodOrderFragment()).commit();
    }

    private class EventListener {
    }
}


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MenuAdapter(getFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }*/

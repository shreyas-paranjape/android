package restaurant.order.module.app.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.orm.SugarRecord;
import com.util.Constant;
import com.util.Util;
import com.view.activity.AbstractActivity;

import model.catalogue.ProductCategory;
import restaurant.order.R;
import restaurant.order.module.catalogue.view.fragment.MenuFragment;
import restaurant.order.module.order.view.fragment.CartFragment;

public class HomeActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullScreen();
        super.onCreate(savedInstanceState);
        initCart(R.id.sliding_layout, R.id.cartContainer, new CartFragment());
        initView();
    }

    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    protected int getContentContainerId() {
        return R.id.container;
    }

    private void initView() {
        initToolbar();
        replaceContent(MenuFragment.class,
                Util.bundleSerializable(
                        Constant.PRODUCT_CATEGORY,
                        SugarRecord.findById(ProductCategory.class, 1)));
    }

    private void initToolbar() {
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
}

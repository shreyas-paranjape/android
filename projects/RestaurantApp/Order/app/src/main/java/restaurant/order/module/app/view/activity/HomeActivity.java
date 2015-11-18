package restaurant.order.module.app.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import restaurant.order.R;
import restaurant.order.module.catalogue.view.component.MenuFragment;

public class HomeActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.addFragment(new MenuFragment(), "Food");
        adapter.addFragment(new MenuFragment(), "Drinks");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    /*private CartManager cartManager;
    private final EventListener listener = new EventListener();

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartManager = new CartManager((SlidingUpPanelLayout) findViewById(R.id.sliding_layout));
        cartManager.hidePanel();
        //registerListener(cartManager);
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
        //unRegisterListener(listener, cartManager);
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
        ft.replace(R.id.cartContainer, new FoodOrderFragment()).commit();
    }

    private class EventListener {
    }*/
}

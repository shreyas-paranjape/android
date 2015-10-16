package in.co.foodamigo.admin.module.app.view.component;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.view.fragment.NavigationDrawer;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.admin.R;
import in.co.foodamigo.admin.module.app.Constant;
import in.co.foodamigo.admin.module.catalogue.view.adapter.list.ProdCatListAdapter;
import in.co.foodamigo.admin.module.catalogue.view.adapter.list.ProdListAdapter;
import in.co.foodamigo.admin.module.catalogue.view.adapter.list.SupplierListAdapter;
import in.co.foodamigo.admin.module.catalogue.view.component.form.ProdCatFormFragment;
import in.co.foodamigo.admin.module.catalogue.view.component.form.ProdFormFragment;
import in.co.foodamigo.admin.module.catalogue.view.component.form.SupplierFormFragment;
import in.co.foodamigo.admin.module.catalogue.view.component.list.ProductListFragment;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_home);
        initView();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack(1, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            super.onBackPressed();
        }
    }

    private void initView() {
        setTitle("");
        EventBus.getDefault().post(
                new NavigationDrawer.SetupDrawerEvent(
                        (DrawerLayout) findViewById(R.id.drawer_layout), R.id.navigation_drawer));
        setupToolbar();
        replaceContent(new ProductListFragment());
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.BLACK);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    private void replaceContent(Fragment newFragment) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, newFragment, newFragment.getClass().getName())
                .addToBackStack(newFragment.getClass().getName())
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(NavigationDrawer.DrawerItemClickedEvent event) {
        getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        replaceContent(event.getItem().getDisplayFragment());
    }

    public void onEvent(ProdCatListAdapter.ProdCatEvent event) {
        ProdCatFormFragment formFragment = new ProdCatFormFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constant.PRODUCT_CATEGORY, event.getProductCategory());
        formFragment.setArguments(args);
        replaceContent(formFragment);
    }

    public void onEvent(ProdListAdapter.ProdEvent event) {
        ProdFormFragment formFragment = new ProdFormFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constant.PRODUCT, event.getProduct());
        formFragment.setArguments(args);
        replaceContent(formFragment);
    }

    public void onEvent(SupplierListAdapter.SupplierEvent event) {
        SupplierFormFragment formFragment = new SupplierFormFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constant.PRODUCT, event.getSupplier());
        formFragment.setArguments(args);
        replaceContent(formFragment);
    }
}

package in.co.foodamigo.admin.module.app.view.component;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.view.activity.AbstractActivity;
import com.view.fragment.NavigationDrawer;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.admin.R;
import in.co.foodamigo.admin.module.app.infra.socket.SocketConnectionManager;
import in.co.foodamigo.admin.module.app.singleton.Constant;
import in.co.foodamigo.admin.module.catalogue.view.adapter.list.ProdCatListAdapter;
import in.co.foodamigo.admin.module.catalogue.view.adapter.list.ProdListAdapter;
import in.co.foodamigo.admin.module.catalogue.view.adapter.list.SupplierListAdapter;
import in.co.foodamigo.admin.module.catalogue.view.component.form.ProdCatFormFragment;
import in.co.foodamigo.admin.module.catalogue.view.component.form.ProdFormFragment;
import in.co.foodamigo.admin.module.catalogue.view.component.form.SupplierFormFragment;
import in.co.foodamigo.admin.module.catalogue.view.component.list.ProductListFragment;

public class HomeActivity extends AbstractActivity {

    private static final String TAG = HomeActivity.class.getName();
    private final EventListener eventListener = new EventListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerListener(eventListener);
        startService(new Intent(this, SocketConnectionManager.class));
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack(1, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterListener(eventListener);
    }

    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    protected int getDrawerFragmentId() {
        return R.id.navigation_drawer;
    }

    protected int getToolbarId() {
        return R.id.toolbar;
    }

    protected Fragment getInitContent() {
        return new ProductListFragment();
    }

    protected int getContentContainerId() {
        return R.id.container;
    }

    protected DrawerLayout getDrawerLayout() {
        return (DrawerLayout) findViewById(R.id.drawer_layout);
    }


    private class EventListener {

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
}

package admin.module.app.view.fragment;

import android.app.Fragment;

import com.view.adapter.list.AbstractListAdapter;
import com.view.fragment.ListNavigationDrawer;
import com.view.model.Item;

import admin.module.app.view.adapter.DrawerAdapter;
import admin.module.catalogue.view.component.list.ProdCatListFragment;
import admin.module.catalogue.view.component.list.ProductListFragment;
import admin.module.order.view.component.OrderListFragment;
import admin.module.party.view.component.list.SupplierListFragment;
import in.co.foodamigo.admin.R;


public class DrawerFragment extends ListNavigationDrawer {

    @Override
    protected int getListViewId() {
        return R.id.elv_drawer;
    }

    @Override
    protected AbstractListAdapter<Item> getAdapter() {
        return new DrawerAdapter(getActivity(), drawerItems);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_drawer;
    }

    @Override
    protected int getCloseContentDescRes() {
        return R.string.navigation_drawer_close;
    }

    @Override
    protected int getOpenContentDescRes() {
        return R.string.navigation_drawer_open;
    }

    static {
        drawerItems.add(new Item("Orders", R.drawable.ic_mode_edit_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new OrderListFragment();
            }
        });

        drawerItems.add(new Item("Products", R.drawable.ic_mode_edit_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new ProductListFragment();
            }
        });

        drawerItems.add(new Item("Categories", R.drawable.ic_mode_edit_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new ProdCatListFragment();
            }
        });

        drawerItems.add(new Item("Suppliers", R.drawable.ic_mode_edit_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new SupplierListFragment();
            }
        });
    }
}

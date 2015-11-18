package in.co.foodamigo.admin.module.app.view.component;

import android.app.Fragment;

import com.view.fragment.ListNavigationDrawer;
import com.view.model.Item;
import com.view.widget.NavigationDrawerListAdapter;

import in.co.foodamigo.admin.R;
import in.co.foodamigo.admin.module.app.view.adapter.DrawerAdapter;
import in.co.foodamigo.admin.module.catalogue.view.component.list.ProdCatListFragment;
import in.co.foodamigo.admin.module.catalogue.view.component.list.ProductListFragment;
import in.co.foodamigo.admin.module.order.view.component.OrderListFragment;
import in.co.foodamigo.admin.module.party.view.component.list.SupplierListFragment;


public class DrawerFragment extends ListNavigationDrawer {

    public DrawerFragment() {
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

    @Override
    protected int getListViewId() {
        return R.id.elv_drawer;
    }

    @Override
    protected NavigationDrawerListAdapter getAdapter() {
        return new DrawerAdapter(
                getActivity(),
                R.layout.item_drawer_link,
                drawerItems);
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
}

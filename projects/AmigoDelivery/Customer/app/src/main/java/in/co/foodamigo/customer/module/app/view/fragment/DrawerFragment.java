package in.co.foodamigo.customer.module.app.view.fragment;

import android.app.Fragment;
import android.os.Bundle;

import com.view.fragment.ListNavigationDrawer;
import com.view.model.Item;
import com.view.widget.NavigationDrawerListAdapter;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.app.view.adapter.DrawerAdapter;
import in.co.foodamigo.customer.module.catalogue.view.component.MenuFragment;
import in.co.foodamigo.customer.module.order.view.component.OrderStatusFragment;
import in.co.foodamigo.customer.module.order.view.component.PastOrderFragment;
import in.co.foodamigo.customer.module.profile.view.component.ProfileFragment;

public class DrawerFragment extends ListNavigationDrawer {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDrawer();
    }

    @Override
    protected int getListViewId() {
        return R.id.elv_drawer;
    }

    @Override
    protected NavigationDrawerListAdapter getAdapter() {
        return new DrawerAdapter(getActivity(), R.layout.item_drawer_link, drawerItems);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_drawer;
    }

    private void initDrawer() {
        drawerItems.add(new Item("Menu", R.drawable.ic_restaurant_menu_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new MenuFragment();
            }
        });
        drawerItems.add(new Item("Profile", R.drawable.ic_account_circle_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new ProfileFragment();
            }
        });
        drawerItems.add(new Item("Pending order", R.drawable.ic_hourglass_empty_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new OrderStatusFragment();
            }
        });
        drawerItems.add(new Item("Past order", R.drawable.ic_hourglass_full_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new PastOrderFragment();
            }
        });
    }


}

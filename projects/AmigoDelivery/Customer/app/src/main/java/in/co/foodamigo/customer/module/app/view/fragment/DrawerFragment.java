package in.co.foodamigo.customer.module.app.view.fragment;

import android.app.Fragment;
import android.os.Bundle;

import com.view.adapter.list.AbstractListAdapter;
import com.view.fragment.ListNavigationDrawer;
import com.view.model.Item;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.app.view.adapter.DrawerAdapter;
import in.co.foodamigo.customer.module.catalogue.view.component.MenuFragment;
import in.co.foodamigo.customer.module.order.view.fragment.OrderStatusFragment;
import in.co.foodamigo.customer.module.order.view.fragment.PastOrderFragment;
import in.co.foodamigo.customer.module.party.view.component.ProfileFragment;

public class DrawerFragment extends ListNavigationDrawer {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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

    static {
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
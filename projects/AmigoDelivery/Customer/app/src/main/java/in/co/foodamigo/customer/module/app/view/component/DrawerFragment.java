package in.co.foodamigo.customer.module.app.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.ListView;

import com.view.fragment.ListNavigationDrawer;
import com.view.model.Item;
import com.view.widget.NavigationDrawerListAdapter;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.app.view.adapter.DrawerAdapter;
import in.co.foodamigo.customer.module.catalogue.view.component.MenuFragment;
import in.co.foodamigo.customer.module.profile.view.component.ProfileFragment;

public class DrawerFragment extends ListNavigationDrawer {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawerItems.add(new Item("Feedback", R.drawable.ic_feedback_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new ProfileFragment();
            }
        });
        drawerItems.add(new Item("Help", R.drawable.ic_help_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return new MenuFragment();
            }
        });
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
}

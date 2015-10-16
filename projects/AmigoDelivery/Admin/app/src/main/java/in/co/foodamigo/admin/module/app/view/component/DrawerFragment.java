package in.co.foodamigo.admin.module.app.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.view.fragment.NavigationDrawer;
import com.view.model.Item;

import java.util.ArrayList;
import java.util.List;

import in.co.foodamigo.admin.R;
import in.co.foodamigo.admin.module.app.view.adapter.DrawerAdapter;
import in.co.foodamigo.admin.module.catalogue.view.component.list.ProdCatListFragment;
import in.co.foodamigo.admin.module.catalogue.view.component.list.ProductListFragment;
import in.co.foodamigo.admin.module.catalogue.view.component.list.SupplierListFragment;


public class DrawerFragment extends NavigationDrawer {

    private List<Item> drawerItems = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawerItems.add(new Item("Products") {
            @Override
            public Fragment getDisplayFragment() {
                return new ProductListFragment();
            }
        });

        drawerItems.add(new Item("Categories") {
            @Override
            public Fragment getDisplayFragment() {
                return new ProdCatListFragment();
            }
        });

        drawerItems.add(new Item("Suppliers") {
            @Override
            public Fragment getDisplayFragment() {
                return new SupplierListFragment();
            }
        });
    }

    @Override
    protected void initView(View v) {
        ListView mDrawerListView = (ListView) v.findViewById(R.id.elv_drawer);
        mDrawerListView.setAdapter(
                new DrawerAdapter(
                        getActivity(),
                        R.layout.item_drawer_link,
                        drawerItems));
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawerItemClicked(drawerItems.get(position));
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_drawer;
    }
}

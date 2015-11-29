package admin.module.catalogue.view.component.list;

import android.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.view.adapter.recycler.AbstractRecyclerAdapter;
import com.view.adapter.spinner.ItemSpinnerAdapter;
import com.view.fragment.AbstractFilterFragment;
import com.view.fragment.AbstractRecyclerFragment;
import com.view.model.Item;

import java.util.ArrayList;
import java.util.List;

import admin.module.catalogue.view.adapter.list.ProdListAdapter;
import admin.module.catalogue.view.component.dialog.ProductFilterDialogue;
import in.co.foodamigo.admin.R;
import model.catalogue.Product;
import repository.catalogue.ProductRepo;

public class ProductListFragment extends AbstractRecyclerFragment<Product> {

    protected final static List<Item> spinnerItems = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.layout_list;
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_home;
    }

    @Override
    protected int getRecyclerId() {
        return R.id.lvGrid;
    }

    @Override
    protected Class getFilterFragmentClass() {
        return null;
    }

    @Override
    protected AbstractRecyclerAdapter getAdapter() {
        return new ProdListAdapter(getActivity(), ProductRepo.getAll());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_filter:
                showDialog();
                // onFilterClick();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView(View root) {
        LinearLayout tlSorts = (LinearLayout) root.findViewById(R.id.llSorts);
        Button textView = new Button(getActivity());
        textView.setText("ProductSort one");
        tlSorts.addView(textView);
        initSpinner();
    }

    private void initSpinner() {
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.spCategories);
        final ItemSpinnerAdapter adapter = new ItemSpinnerAdapter(getActivity(), spinnerItems);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Item item = adapter.getItem(position);
                //Todo Filter list based on category
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner.setAdapter(adapter);
    }

    protected AbstractFilterFragment<Product> getFilterFragment() {
        return new ProductFilterDialogue();
    }

    static {
        spinnerItems.add(new Item("Pending", R.drawable.ic_mode_edit_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        spinnerItems.add(new Item("Past", R.drawable.ic_account_circle_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
        spinnerItems.add(new Item("Today", R.drawable.ic_add_shopping_cart_black_24dp) {
            @Override
            public Fragment getDisplayFragment() {
                return null;
            }
        });
    }

}

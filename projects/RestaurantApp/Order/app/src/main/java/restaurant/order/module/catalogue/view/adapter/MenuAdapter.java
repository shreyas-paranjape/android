package restaurant.order.module.catalogue.view.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.util.Constant;
import com.util.IPredicate;

import java.util.Iterator;
import java.util.List;

import model.catalogue.ProductCategory;
import repository.catalogue.ProductCategoryRepo;
import restaurant.order.module.catalogue.view.component.MenuFragment;

public class MenuAdapter extends FragmentStatePagerAdapter {

    private final List<ProductCategory> menuCategories;

    public MenuAdapter(FragmentManager fm) {
        super(fm);
        this.menuCategories = ProductCategoryRepo.getAll();
        applyFilter(menuCategories, new IPredicate<ProductCategory>() {
            @Override
            public boolean apply(ProductCategory type) {
                return type.getParent() != null;
            }
        });
    }

    @Override
    public android.app.Fragment getItem(int position) {
        return getCategoryFragment(position);
    }

    @Override
    public int getCount() {
        return menuCategories != null ? menuCategories.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return menuCategories != null ? menuCategories.get(position).getName() : "";
    }

    @NonNull
    private Fragment getCategoryFragment(int position) {
        MenuFragment fragment = new MenuFragment();
        setArgs(position, fragment);
        return fragment;
    }

    private void setArgs(int position, Fragment fragment) {
        if (menuCategories != null && position < menuCategories.size()) {
            Bundle args = new Bundle();
            args.putSerializable(Constant.PRODUCT_CATEGORY, menuCategories.get(position));
            fragment.setArguments(args);
        }
    }

    private static List<ProductCategory> applyFilter(
            List<ProductCategory> objects,
            IPredicate<ProductCategory> filter) {
        Iterator<ProductCategory> iterator = objects.iterator();
        while (iterator.hasNext()) {
            if (filter.apply(iterator.next())) {
                iterator.remove();
            }
        }
        return objects;
    }

}

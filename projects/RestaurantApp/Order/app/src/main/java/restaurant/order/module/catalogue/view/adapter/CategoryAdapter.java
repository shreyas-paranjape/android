package restaurant.order.module.catalogue.view.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.util.Constant;

import java.util.List;

import model.catalogue.ProductCategory;
import repository.catalogue.ProductCategoryRepo;
import restaurant.order.module.catalogue.view.fragment.CategoryFragment;


public class CategoryAdapter extends FragmentStatePagerAdapter {

    private final List<ProductCategory> rootCategories;

    public CategoryAdapter(FragmentManager fm) {
        super(fm);
        this.rootCategories = ProductCategoryRepo.getAll();
    }

    public CategoryAdapter(FragmentManager fm, ProductCategory parent) {
        super(fm);
        this.rootCategories = ProductCategoryRepo.getAllChildren(parent.getId());
    }

    @Override
    public android.app.Fragment getItem(int position) {
        return getCategoryFragment(position);
    }

    @Override
    public int getCount() {
        return rootCategories != null ? rootCategories.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return rootCategories != null ? rootCategories.get(position).getName() : "";
    }

    @NonNull
    private Fragment getCategoryFragment(int position) {
        CategoryFragment fragment = new CategoryFragment();
        setArgs(position, fragment);
        return fragment;
    }

    private void setArgs(int position, Fragment fragment) {
        if (rootCategories != null && position < rootCategories.size()) {
            Bundle args = new Bundle();
            args.putSerializable(Constant.PRODUCT_CATEGORY, rootCategories.get(position));
            fragment.setArguments(args);
        }
    }

}
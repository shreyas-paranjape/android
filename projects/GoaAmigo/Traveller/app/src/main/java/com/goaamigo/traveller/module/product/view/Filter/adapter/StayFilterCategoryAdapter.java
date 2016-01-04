package com.goaamigo.traveller.module.product.view.Filter.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.goaamigo.traveller.module.product.view.fragment.FilterListFragment;
import com.goaamigo.traveller.module.product.view.fragment.StayPrice;
import com.goaamigo.traveller.module.product.view.fragment.AmenitiesFragment;
import com.goaamigo.traveller.module.product.view.fragment.PropertyTypeFragment;

public class StayFilterCategoryAdapter extends FragmentStatePagerAdapter {
    private static final String tabTitles[] = new String[]{"Price & Details", "Amenities", "Property Type", "Location"};

    public StayFilterCategoryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment obj = null;
        if (position == 0) {
            obj = new StayPrice();
        } else if (position == 1) {
            obj = new AmenitiesFragment();
        } else if (position == 2) {
            obj = new PropertyTypeFragment();
        } else if (position == 3) {
            obj = new FilterListFragment();
        }
        return obj;
    }

    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
}

package com.goaamigo.traveller.module.product.view.Filter.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.goaamigo.traveller.module.product.view.fragment.FilterListFragment;
import com.goaamigo.traveller.module.product.view.fragment.CuisinesFragment;
import com.goaamigo.traveller.module.product.view.fragment.EstablishmentType;
import com.goaamigo.traveller.module.product.view.fragment.RestAmenities;
import com.goaamigo.traveller.module.product.view.fragment.RestPrice;

public class RestFilterCategoryAdapter extends FragmentStatePagerAdapter {
    private static final String tabTitles[] = new String[]{"Price & Details", "Cuisines ", "Establishment type", "amenities","Location"};

    public RestFilterCategoryAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment obj = null;
        if (position == 0) {
            obj = new RestPrice();
        } else if (position == 1) {
            obj = new CuisinesFragment();
        } else if (position == 2) {
            obj = new EstablishmentType();
        } else if (position == 3) {
            obj = new RestAmenities();
        } else if (position == 4) {
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

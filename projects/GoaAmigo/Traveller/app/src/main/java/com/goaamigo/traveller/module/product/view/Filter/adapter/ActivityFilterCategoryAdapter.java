package com.goaamigo.traveller.module.product.view.Filter.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.goaamigo.traveller.module.product.view.fragment.FilterListFragment;
import com.goaamigo.traveller.module.product.view.fragment.ActivityPrice;
import com.goaamigo.traveller.module.product.view.fragment.ActivityType;

import java.io.Serializable;

public class ActivityFilterCategoryAdapter
        extends FragmentStatePagerAdapter
        implements Serializable {
    private static final String tabTitles[] =
            new String[]{"Price & Details", "Type of Activity", "Location"};
    private static final long serialVersionUID = 1l;

    public ActivityFilterCategoryAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment obj = null;
        if (position == 0) {
            obj = new ActivityPrice();
        } else if (position == 1) {
            obj = new ActivityType();
        } else if (position == 2) {
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

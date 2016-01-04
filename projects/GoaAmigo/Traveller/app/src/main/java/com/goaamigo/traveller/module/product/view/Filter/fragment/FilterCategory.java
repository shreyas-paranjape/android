package com.goaamigo.traveller.module.product.view.Filter.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.Filter.adapter.ActivityFilterCategoryAdapter;
import com.goaamigo.traveller.module.product.view.Filter.adapter.RestFilterCategoryAdapter;
import com.goaamigo.traveller.module.product.view.Filter.adapter.StayFilterCategoryAdapter;
import com.goaamigo.traveller.module.product.view.activity.ProductsActivity;
import com.view.fragment.AbstractFragment;

import de.greenrobot.event.EventBus;

public class FilterCategory extends AbstractFragment {
    private String detail = null;

    public FilterCategory() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product_filter, container, false);
        detail= getArguments().getString("Key");
        initView(v);
        return v;
    }

    protected void initView(View rootView) {
        ViewPager mPager = initPager(rootView);
        initTabs(rootView, mPager);
        Button apply = (Button) rootView.findViewById(R.id.btnApply);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new ProductsActivity.SlidingTabDismissEvent());
            }
        });
        Button cancel = (Button) rootView.findViewById(R.id.btnCancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new ProductsActivity.SlidingTabDismissEvent());
            }
        });
    }

    private void initTabs(View rootView, ViewPager mPager) {
        TabLayout slidingTabLayout =
                (TabLayout) rootView.findViewById(R.id.tabs);
        slidingTabLayout.setupWithViewPager(mPager);
//        slidingTabLayout.setViewPager(mPager);
//        slidingTabLayout.setDistributeEvenly(true);
//        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
//            @Override
//            public int getIndicatorColor(int position) {
//                return getResources().getColor(R.color.indicator_color);
//            }
//        });
    }

    private ViewPager initPager(View rootView) {
        PagerAdapter mPagerAdapter = null;
        switch (detail) {
            case "Stay":
                mPagerAdapter = new
                        StayFilterCategoryAdapter(getActivity().getFragmentManager());
                break;
            case "Restaurant":
                mPagerAdapter = new
                        RestFilterCategoryAdapter(getActivity().getFragmentManager());
                break;
            case "Activities":
                mPagerAdapter = new
                        ActivityFilterCategoryAdapter(getActivity().getFragmentManager());
                break;
        }

        ViewPager mPager = (ViewPager) rootView.findViewById(R.id.pager);
        mPager.setAdapter(mPagerAdapter);
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                getActivity().invalidateOptionsMenu();
            }
        });
        return mPager;
    }
}

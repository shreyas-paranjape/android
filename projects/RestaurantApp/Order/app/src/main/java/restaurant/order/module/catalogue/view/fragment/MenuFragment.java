package restaurant.order.module.catalogue.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.util.Constant;

import model.catalogue.ProductCategory;
import restaurant.order.R;
import restaurant.order.module.catalogue.view.adapter.CategoryAdapter;


public class MenuFragment extends Fragment {

    private ProductCategory menuCategory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            menuCategory = (ProductCategory) getArguments()
                    .getSerializable(Constant.PRODUCT_CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("");
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        ViewPager mPager = initPager(rootView);
        initTabs(rootView, mPager);
    }

    private void initTabs(View rootView, ViewPager mPager) {
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabsInner);
        tabLayout.setupWithViewPager(mPager);
    }

    @NonNull
    private ViewPager initPager(View rootView) {
        PagerAdapter mPagerAdapter =
                getCategoryAdapter();
        ViewPager mPager = (ViewPager) rootView.findViewById(R.id.vpSecond);
        mPager.setAdapter(mPagerAdapter);
        mPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                getActivity().invalidateOptionsMenu();
            }
        });
        return mPager;
    }

    @NonNull
    private CategoryAdapter getCategoryAdapter() {
        if (menuCategory != null) {
            return new CategoryAdapter(getActivity().getFragmentManager(), menuCategory);
        }
        return new CategoryAdapter(getActivity().getFragmentManager());
    }

    public static class ShowOrderStatusEvent {
    }

}

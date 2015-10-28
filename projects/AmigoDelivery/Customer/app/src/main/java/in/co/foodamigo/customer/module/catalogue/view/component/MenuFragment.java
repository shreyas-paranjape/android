package in.co.foodamigo.customer.module.catalogue.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.view.widget.SlidingTabLayout;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.app.view.event.ChangeContentEvent;
import in.co.foodamigo.customer.module.catalogue.view.adapter.CategoryAdapter;
import in.co.foodamigo.customer.module.profile.view.component.ProfileFragment;


public class MenuFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_profile:
                showProfileView();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showProfileView() {
        EventBus.getDefault().post(new ChangeContentEvent(ChangeContentEvent.ContentType.FRAGMENT, null) {
            @Override
            public Class getContentClass() {
                return ProfileFragment.class;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Menu");
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        ViewPager mPager = initPager(rootView);
        initTabs(rootView, mPager);
    }

    private void initTabs(View rootView, ViewPager mPager) {
        SlidingTabLayout slidingTabLayout =
                (SlidingTabLayout) rootView.findViewById(R.id.tabs);
        slidingTabLayout.setViewPager(mPager);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.indicator_color);
            }

            @Override
            public int getDividerColor(int position) {
                return getResources().getColor(R.color.primary_color);
            }
        });
    }

    @NonNull
    private ViewPager initPager(View rootView) {
        PagerAdapter mPagerAdapter = new CategoryAdapter(getActivity().getFragmentManager());
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

    public static class ShowOrderStatusEvent {
    }

}

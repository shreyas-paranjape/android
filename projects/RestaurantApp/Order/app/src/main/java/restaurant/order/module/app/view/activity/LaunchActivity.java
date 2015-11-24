package restaurant.order.module.app.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import me.relex.circleindicator.CircleIndicator;
import restaurant.order.R;
import restaurant.order.module.app.view.adapter.TutorialPagerAdapter;

public class LaunchActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initView();
    }

    private void initView() {
        ViewPager defaultViewpager = (ViewPager) findViewById(R.id.viewpager_default);
        CircleIndicator defaultIndicator = (CircleIndicator) findViewById(R.id.indicator_default);
        TutorialPagerAdapter adapter = new TutorialPagerAdapter(getFragmentManager());
        defaultViewpager.setAdapter(adapter);
        defaultIndicator.setViewPager(defaultViewpager);
    }
}

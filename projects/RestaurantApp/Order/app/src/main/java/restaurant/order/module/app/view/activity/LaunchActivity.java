package restaurant.order.module.app.view.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.google.gson.reflect.TypeToken;
import com.view.activity.AbstractActivity;

import java.util.List;
import java.util.Random;

import me.relex.circleindicator.CircleIndicator;
import model.catalogue.Product;
import model.catalogue.ProductCategory;
import rest.RestResource;
import restaurant.order.R;
import restaurant.order.module.app.view.adapter.TutorialPagerAdapter;

public class LaunchActivity extends AbstractActivity {

    private Random random = new Random();

    private final int productRequestCode = 99;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        refreshProductCatalogue();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    private void initView() {
        ViewPager defaultViewpager = (ViewPager) findViewById(R.id.viewpager_default);
        CircleIndicator defaultIndicator = (CircleIndicator) findViewById(R.id.indicator_default);
        TutorialPagerAdapter adapter = new TutorialPagerAdapter(getFragmentManager());
        defaultViewpager.setAdapter(adapter);
        defaultIndicator.setViewPager(defaultViewpager);
    }

    @SuppressWarnings("unused")
    public void onEvent(RestResource.HttpRequestComplete event) {
        switch (event.getRequestId()) {
            case productRequestCode:
                startNewActivity(HomeActivity.class);
                finish();
                break;
            default:
                break;
        }
    }

    private void refreshProductCatalogue() {
        new RestResource<ProductCategory>(getApp().getBaseUri() + "/category")
                .getAllAndPersist(random.nextInt(), getApp().getQueue(),
                        new TypeToken<List<ProductCategory>>() {
                        }.getType());

        new RestResource<Product>(getApp().getBaseUri() + "/product")
                .getAllAndPersist(productRequestCode, getApp().getQueue(),
                        new TypeToken<List<Product>>() {
                        }.getType());
    }
}

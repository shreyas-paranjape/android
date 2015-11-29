package restaurant.order.module.order.view.activity;

import android.app.Fragment;
import android.os.Bundle;

import com.view.activity.AbstractActivity;

import restaurant.order.R;

public class CheckoutActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_layout_container;
    }

    @Override
    protected Fragment getInitContent() {
        return null;
    }

    @Override
    protected int getContentContainerId() {
        return R.id.container;
    }
}

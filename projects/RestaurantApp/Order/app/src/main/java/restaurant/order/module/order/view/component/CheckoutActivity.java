package restaurant.order.module.order.view.component;

import android.app.Fragment;
import android.os.Bundle;

import com.order.CurrentOrderManager;
import com.view.activity.AbstractActivity;

import restaurant.order.R;
import restaurant.order.module.app.singleton.OrderApp;

public class CheckoutActivity extends AbstractActivity {

    private CurrentOrderManager currentOrderManager;
    //private final EventListener eventListener = new EventListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currentOrderManager = ((OrderApp) getApplication()).getCurrentOrderManager();
        super.onCreate(savedInstanceState);
        //registerListener(eventListener);
    }

    @Override
    protected void onDestroy() {
        //unRegisterListener(eventListener);
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_layout_container;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected Fragment getInitContent() {
        return null;
    }

    @Override
    protected int getContentContainerId() {
        return R.id.container;
    }

    @Override
    protected int getTitleId() {
        return R.id.tvTitle;
    }

    private class EventListener {

    }
}

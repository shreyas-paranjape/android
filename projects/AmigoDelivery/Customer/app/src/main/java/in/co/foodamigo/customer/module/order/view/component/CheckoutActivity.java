package in.co.foodamigo.customer.module.order.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.view.activity.AbstractActivity;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.app.singleton.CustomerApp;
import in.co.foodamigo.customer.module.order.controller.OrderManager;

public class CheckoutActivity extends AbstractActivity {

    private OrderManager orderManager;
    private final EventListener eventListener = new EventListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderManager = ((CustomerApp) getApplication()).getOrderManager();
        registerListener(eventListener);
    }

    @Override
    protected void onDestroy() {
        unRegisterListener(eventListener);
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
        return new DeliveryAddressFragment();
    }

    @Override
    protected int getContentContainerId() {
        return R.id.container;
    }

    @Override
    protected DrawerLayout getDrawerLayout() {
        return null;
    }

    @Override
    protected int getDrawerFragmentId() {
        return 0;
    }

    private class EventListener {

        public void onEvent(DeliveryAddressFragment.AddressSelectedEvent event) {
            orderManager.setDeliveryAddress(event.getAddress());
            replaceContent(new ConfirmOrderFragment());
        }
    }
}

package in.co.foodamigo.customer.module.order.view.component;

import android.app.Fragment;
import android.os.Bundle;

import com.order.CurrentOrderManager;
import com.view.activity.AbstractActivity;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.app.singleton.CustomerApp;

public class CheckoutActivity extends AbstractActivity {

    private CurrentOrderManager currentOrderManager;
    private final EventListener eventListener = new EventListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currentOrderManager = ((CustomerApp) getApplication()).getCurrentOrderManager();
        super.onCreate(savedInstanceState);
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
        return new DeliveryLocationFragment();
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

        public void onEvent(DeliveryLocationFragment.AddressSelectedEvent event) {
            //TODO currentOrderManager.setDeliveryAddress(event.getLocation());
            replaceContent(new ConfirmOrderFragment());
        }
    }
}

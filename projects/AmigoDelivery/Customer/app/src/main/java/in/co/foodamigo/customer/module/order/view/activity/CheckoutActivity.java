package in.co.foodamigo.customer.module.order.view.activity;

import android.app.Fragment;
import android.os.Bundle;

import com.view.activity.AbstractActivity;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.order.view.fragment.ConfirmOrderFragment;
import in.co.foodamigo.customer.module.order.view.fragment.DeliveryLocationFragment;

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

    @SuppressWarnings("unused")
    public void onEvent(DeliveryLocationFragment.AddressSelectedEvent event) {
        //TODO currentOrderManager.setDeliveryAddress(event.getLocation());
        replaceContent(new ConfirmOrderFragment());
    }
}

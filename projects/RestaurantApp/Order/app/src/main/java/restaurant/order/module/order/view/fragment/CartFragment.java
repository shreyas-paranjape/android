package restaurant.order.module.order.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.order.CurrentOrderManager;
import com.view.activity.AppActivity;
import com.view.fragment.AbstractFragment;

import de.greenrobot.event.EventBus;
import restaurant.order.databinding.FragmentOrderBinding;
import restaurant.order.module.order.view.adapter.FoodCartItemAdapter;

public class CartFragment extends AbstractFragment {

    protected CurrentOrderManager currentOrderManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        currentOrderManager = getApp().getCurrentOrderManager();
        initCart();
        FragmentOrderBinding rootBinding = FragmentOrderBinding.inflate(inflater);
        initView(rootBinding);
        return rootBinding.getRoot();
    }

    private void initCart() {
        if (currentOrderManager.cartSize() > 0) {
            EventBus.getDefault().post(
                    new CurrentOrderManager.CartModifiedEvent(currentOrderManager.cartSize()));
        }
    }

    private void initView(FragmentOrderBinding rootBinding) {
        rootBinding.lvCartItems.setAdapter(
                new FoodCartItemAdapter(
                        getActivity(),
                        currentOrderManager.getOrder().getOrderItems()));
        rootBinding.setOrderManager(currentOrderManager);
        rootBinding.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentOrderManager.checkOut();
            }
        });
    }
}
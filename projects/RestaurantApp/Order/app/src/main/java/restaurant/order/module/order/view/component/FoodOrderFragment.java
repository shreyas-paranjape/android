package restaurant.order.module.order.view.component;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.order.CurrentOrderManager;
import com.order.OrderFragment;

import de.greenrobot.event.EventBus;
import restaurant.order.databinding.FragmentOrderBinding;
import restaurant.order.module.app.singleton.OrderApp;
import restaurant.order.module.order.view.adapter.FoodCartItemAdapter;

public class FoodOrderFragment extends OrderFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        rootBinding.lvCartItems.setAdapter(cartItemsAdapter);
        rootBinding.setOrderManager(currentOrderManager);
        rootBinding.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentOrderManager.checkOut();
            }
        });
    }

    @Override
    protected com.order.CurrentOrderManager getOrderManager() {
        return ((OrderApp) getActivity().getApplication())
                .getCurrentOrderManager();
    }


    @NonNull
    protected FoodCartItemAdapter getAdapter() {
        return new FoodCartItemAdapter(
                getActivity(),
                currentOrderManager.getOrder().getOrderItems());
    }


}
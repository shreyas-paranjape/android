package in.co.foodamigo.customer.module.order.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.databinding.FragmentOrderBinding;
import in.co.foodamigo.customer.module.app.singleton.CustomerApp;
import in.co.foodamigo.customer.module.order.controller.CurrentOrderManager;
import in.co.foodamigo.customer.module.order.view.adapter.CartItemAdapter;

public class OrderFragment extends Fragment {

    private CurrentOrderManager currentOrderManager;
    private ArrayAdapter cartItemsAdapter;

    public OrderFragment() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initCart();
        FragmentOrderBinding rootBinding = FragmentOrderBinding.inflate(inflater);
        initView(rootBinding);
        return rootBinding.getRoot();
    }

    private void initCart() {
        currentOrderManager = ((CustomerApp) getActivity().getApplication()).getCurrentOrderManager();
        if (currentOrderManager.cartSize() > 0) {
            EventBus.getDefault().post(
                    new CurrentOrderManager.CartModifiedEvent(currentOrderManager.cartSize()));
        }
    }

    private void initView(FragmentOrderBinding rootBinding) {
        cartItemsAdapter = getCartItemAdapter();
        rootBinding.lvCartItems.setAdapter(cartItemsAdapter);
        rootBinding.setOrder(currentOrderManager.getOrder());
        rootBinding.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentOrderManager.checkOut();
            }
        });
    }

    @NonNull
    private CartItemAdapter getCartItemAdapter() {
        return new CartItemAdapter(
                getActivity(),
                R.layout.item_order_current,
                currentOrderManager.getOrder().getOrderItems());
    }

    public void onEvent(CurrentOrderManager.ModifyCartEvent event) {
        cartItemsAdapter.notifyDataSetChanged();
    }


}
package in.co.foodamigo.customer.module.order.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import delivery.model.order.OrderItem;
import in.co.foodamigo.customer.databinding.FragmentConfirmOrderBinding;
import in.co.foodamigo.customer.databinding.ItemOrderConfirmBinding;
import in.co.foodamigo.customer.module.app.singleton.CustomerApp;
import in.co.foodamigo.customer.module.order.controller.CurrentOrderManager;

public class ConfirmOrderFragment extends Fragment {

    private CurrentOrderManager currentOrderManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentOrderManager = ((CustomerApp) getActivity().getApplication()).getCurrentOrderManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Confirm order");
        FragmentConfirmOrderBinding rootBinding = FragmentConfirmOrderBinding.inflate(inflater);
        rootBinding.setOrder(currentOrderManager.getOrder());
        rootBinding.btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentOrderManager.placeOrder();
            }
        });
        setOrderItems(rootBinding.lvOrderItems, inflater);
        return rootBinding.getRoot();
    }

    public void setOrderItems(LinearLayout orderItemsContainer, LayoutInflater inflater) {
        for (OrderItem orderItem : currentOrderManager.getOrder().getOrderItems()) {
            ItemOrderConfirmBinding rootBinding = ItemOrderConfirmBinding.inflate(inflater);
            rootBinding.setOrderItem(orderItem);
            orderItemsContainer.addView(rootBinding.getRoot());
        }
    }
}

package restaurant.order.module.order.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.order.CurrentOrderManager;

import restaurant.order.databinding.FragmentConfirmOrderBinding;
import restaurant.order.databinding.ItemOrderConfirmBinding;
import restaurant.order.module.app.singleton.OrderApp;
import model.order.OrderItem;

public class ConfirmOrderFragment extends Fragment {

    private CurrentOrderManager currentOrderManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentOrderManager = ((OrderApp) getActivity().getApplication()).getCurrentOrderManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Confirm order");
        FragmentConfirmOrderBinding rootBinding = FragmentConfirmOrderBinding.inflate(inflater);
        rootBinding.setOrderManager(currentOrderManager);
        rootBinding.btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO currentOrderManager.placeOrder();
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

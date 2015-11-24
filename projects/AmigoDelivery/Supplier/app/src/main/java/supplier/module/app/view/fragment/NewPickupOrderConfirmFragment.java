package supplier.module.app.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.util.Constant;

import model.order.Order;
import rest.resource.OrderRes;
import supplier.R;
import supplier.module.app.singleton.SupplierApp;

public class NewPickupOrderConfirmFragment extends Fragment {

    private Order order;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        order = (Order) getArguments().getSerializable(Constant.ORDER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirm, container, false);
        Log.d("App", "Order : " + order);
        return v;
    }

    private void placeOrder() {
        OrderRes orderRes = new OrderRes(((SupplierApp) getActivity().getApplication()).getQueue());
        orderRes.placeOrder(order);
    }
}

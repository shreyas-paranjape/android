package in.co.foodamigo.customer.module.order.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.app.singleton.CustomerApp;
import in.co.foodamigo.customer.module.order.controller.OrderManager;

public class ConfirmOrderFragment extends Fragment {

    private OrderManager orderManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderManager = ((CustomerApp) getActivity().getApplication()).getOrderManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_confirm_order, container, false);
        initView(root);
        return root;
    }

    private void initView(View root) {
        ListView lvItems = (ListView) root.findViewById(R.id.lvOrderItems);
        lvItems.setAdapter(new ArrayAdapter<>(
                getActivity(),
                R.layout.item_select_address,
                orderManager.getOrder().getOrderItems()
        ));
        TextView tvAddress = (TextView) root.findViewById(R.id.tvAddress);
        tvAddress.setText(orderManager.getDeliveryAddress().getAddressString());
        TextView tvTotal = (TextView) root.findViewById(R.id.tvTotal);
        tvTotal.setText(orderManager.getOrder().total.toString());
    }
}

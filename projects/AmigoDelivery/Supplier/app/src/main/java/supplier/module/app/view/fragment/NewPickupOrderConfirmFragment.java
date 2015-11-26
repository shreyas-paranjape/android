package supplier.module.app.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.util.Constant;

import model.common.Location;
import model.order.Order;
import model.party.Party;
import rest.resource.OrderRes;
import supplier.R;
import supplier.module.app.singleton.SupplierApp;

public class NewPickupOrderConfirmFragment extends Fragment {

    private Order order;
    //private Party party;
    //private Location loc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        order = (Order) getArguments().getSerializable(Constant.ORDER);
        //party = (Party) getArguments().getSerializable(Constant.PARTY);
        //loc = (Location) getArguments().getSerializable(Constant.LOCATION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirm, container, false);
        Button ok;
        ok = (Button) v.findViewById(R.id.okbtn);

        TextView cname = (TextView) v.findViewById(R.id.cusname);
        TextView cnum = (TextView) v.findViewById(R.id.cusnumber);
        TextView cadd = (TextView) v.findViewById(R.id.add1);
//        TextView textView7 = (TextView)v. findViewById(R.id/.add2);
        TextView camount = (TextView) v.findViewById(R.id.amount);
        TextView cpickup = (TextView) v.findViewById(R.id.pickuptime);
//        TextView caltenum= (TextView)v. findViewById(R.id.alternatnum);

        cname.setText(order.getParty().getName());
        cnum.setText(order.getParty().getMobileNumber());
        cadd.setText(order.getDeliveryLocation().getAddress());
        // textView7.setText((CharSequence) order.getCode());
        camount.setText(Double.valueOf(order.getOrderItems().get(0).getPrice()).toString());
        cpickup.setText(order.getPickupTime() != null ? order.getPickupTime().toString() : "");
        //caltenum.setText((CharSequence) order.getPickupTime());

        Log.d("App", "Order : " + order);
        return v;
    }

    private void placeOrder() {
        OrderRes orderRes = new OrderRes(((SupplierApp) getActivity().getApplication()).getQueue());
        orderRes.placeOrder(order);
    }
}

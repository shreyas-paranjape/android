package supplier.module.app.view.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.event.ChangeContentEvent;
import com.util.Constant;
import com.util.Util;

import java.util.Collections;
import java.util.UUID;

import de.greenrobot.event.EventBus;
import model.common.Location;
import model.order.Order;
import model.order.OrderItem;
import model.party.Party;
import supplier.R;

public class NewPickUpOrderFragment extends Fragment {
    EditText epn, ean, ecn1, ead1, ead2, eam, ept;
    Button btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pickup, container, false);


        epn = (EditText)v.findViewById(R.id.epn);
        ean = (EditText)v. findViewById(R.id.ean);
        ecn1 = (EditText)v. findViewById(R.id.ecn1);
        ead1 = (EditText)v. findViewById(R.id.ead1);
        ead2 = (EditText)v. findViewById(R.id.ead2);
        eam = (EditText)v. findViewById(R.id.eam);
        ept = (EditText)v. findViewById(R.id.ept);
        btn = (Button)v. findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Party party = new Party();
                party.setMobileNumber(epn.getText().toString());
                party.setName(ecn1.getText().toString());
                Location loc = new Location();
                loc.setAddress(ead1.getText().toString());
                OrderItem orderitem=new OrderItem();
                orderitem.setPrice(0);
                onSubmit(party,loc,orderitem);
            }

        });
        return v;
    }




    private void onSubmit(Party party,Location location,OrderItem orderItem) {
        EventBus.getDefault().post(
                new ChangeContentEvent(
                        NewPickupOrderConfirmFragment.class,
                        Util.bundleSerializable(Constant.ORDER, buildOrder(party, location, orderItem))));
    }

    private Order buildOrder(Party party, Location location, OrderItem orderItem) {
        Order order = new Order();
        order.setCode(UUID.randomUUID().toString());
        order.setParty(party);
        order.setDeliveryLocation(location);
        order.setOrderItems(Collections.singletonList(orderItem));
        return order;
    }
}

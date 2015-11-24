package supplier.module.app.view.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pickup, container, false);
        return v;
    }

    private void onSubmit(Party party, Location location, OrderItem orderItem) {
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

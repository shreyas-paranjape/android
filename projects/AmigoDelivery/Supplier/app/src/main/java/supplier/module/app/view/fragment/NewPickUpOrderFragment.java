package supplier.module.app.view.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.event.ChangeContentEvent;
import com.util.Constant;
import com.util.Util;
import com.view.fragment.AbstractFragment;
import com.view.fragment.TimePickerFragment;

import java.io.Serializable;
import java.util.Collections;
import java.util.UUID;

import de.greenrobot.event.EventBus;
import model.common.Location;
import model.order.Order;
import model.order.OrderItem;
import model.party.Party;
import supplier.R;

public class NewPickUpOrderFragment extends AbstractFragment {
    EditText epn, ean, ecn1, ead1, ead2, eam;
    Button btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_pickup, container, false);
        final TextView tvPickupTime = (TextView) v.findViewById(R.id.etPickupTime);
        final TextView tvLocation = (TextView) v.findViewById(R.id.etLocation);
        v.findViewById(R.id.selectTimeContainer)
                .setOnClickListener(showTimePicker(tvPickupTime));
        v.findViewById(R.id.selectLocationContainer)
                .setOnClickListener(showLocationPicker(tvLocation));

        ecn1 = (EditText) v.findViewById(R.id.ecn1);
        ead1 = (EditText) v.findViewById(R.id.ead1);
        ead2 = (EditText) v.findViewById(R.id.ead2);
        eam = (EditText) v.findViewById(R.id.eam);

        btn = (Button) v.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Button clicked", "button clicked");


                Party party = new Party();
                party.setMobileNumber(epn.getText().toString());
                party.setName(ecn1.getText().toString());
                Location loc = new Location();
                loc.setAddress(ead1.getText().toString());
                OrderItem orderitem = new OrderItem();
                orderitem.setPrice(0);
                onSubmit(party, loc, orderitem);
            }

        });
        return v;
    }

    private View.OnClickListener showLocationPicker(final TextView tvLocation) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicker(
                        new LocationPicker(),
                        new LocationPicker.LocationSelectListener() {
                            @Override
                            public void onLocationSelect(Location location) {
                                tvLocation.setText(location.getAddress());
                            }
                        },
                        "locationPicker");
            }
        };
    }


    @NonNull
    private View.OnClickListener showTimePicker(final TextView tvPickupTime) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPicker(
                        new TimePickerFragment(),
                        new TimePickerFragment.TimeSelectListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                tvPickupTime.setText(hourOfDay + " : " + minute);
                            }
                        },
                        "timePicker");
            }
        };
    }

    private void showPicker(DialogFragment fragment, Serializable listener, String tag) {
        Bundle listenerBundle = new Bundle();
        listenerBundle.putSerializable(Constant.PICKER_LISTENER, listener);
        fragment.setArguments(listenerBundle);
        fragment.show(getActivity().getFragmentManager(), tag);
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
        Log.i("Order", order.toString());
        return order;
    }

}

package in.co.foodamigo.customer.module.profile.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import de.greenrobot.event.EventBus;
import delivery.model.common.Address;
import in.co.foodamigo.customer.databinding.ItemAddressBinding;
import in.co.foodamigo.customer.module.app.view.component.FormActivity;

public class AddressAdapter extends ArrayAdapter<Address> {

    protected final LayoutInflater inflater;

    public AddressAdapter(Context context, int resource, List<Address> addressList) {
        super(context, resource, addressList);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemAddressBinding rootBinding =
                ItemAddressBinding.inflate(inflater, parent, false);
        final Address currentAddress = getItem(position);
        rootBinding.setAddress(currentAddress);
        rootBinding.ibEditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new AddressEditClickEvent(currentAddress));
            }
        });
        return rootBinding.getRoot();
    }

    public static class AddressEditClickEvent {
        private final Address address;

        public AddressEditClickEvent(Address address) {
            this.address = address;
        }

        public Address getAddress() {
            return address;
        }
    }
}

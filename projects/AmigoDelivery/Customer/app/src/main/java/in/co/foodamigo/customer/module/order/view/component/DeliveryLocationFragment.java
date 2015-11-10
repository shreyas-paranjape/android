package in.co.foodamigo.customer.module.order.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.order.view.adapter.DeliveryLocationAdapter;
import model.common.Location;

public class DeliveryLocationFragment extends Fragment {

    private final List<Location> savedAddresses;

    public DeliveryLocationFragment() {
        savedAddresses = Arrays.asList(
                new Location(),
                new Location());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Delivery location");
        View v = inflater.inflate(R.layout.fragment_delivery_address, container, false);
        initView(v);
        return v;
    }

    private void initView(View root) {
        ListView addressListView = (ListView) root.findViewById(android.R.id.list);
        addressListView.setAdapter(new DeliveryLocationAdapter(
                getActivity(),
                R.layout.item_select_address,
                savedAddresses));
        addressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                EventBus.getDefault().post(
                        new AddressSelectedEvent(
                                savedAddresses.get(position)));
            }
        });
        if (0 == savedAddresses.size()) {
            ViewGroup emptyContainer = (ViewGroup) root.findViewById(android.R.id.empty);
            emptyContainer.setVisibility(View.VISIBLE);
        }
    }

    public static class AddressSelectedEvent {
        private final Location location;

        public AddressSelectedEvent(Location address) {
            this.location = address;
        }

        public Location getLocation() {
            return location;
        }
    }
}

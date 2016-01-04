package in.co.foodamigo.customer.module.party.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.view.adapter.list.AbstractListAdapter;

import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.customer.databinding.ItemLocationBinding;
import model.common.Location;

public class LocationAdapter extends AbstractListAdapter<Location> {

    public LocationAdapter(Context context, List<Location> locationList) {
        super(context, locationList);
    }

    @Override
    protected View getView(final int position, ViewGroup parent) {
        ItemLocationBinding rootBinding =
                ItemLocationBinding.inflate(inflater, parent, false);
        final Location currentAddress = getItem(position);
        rootBinding.setLocation(currentAddress);
        rootBinding.ibEditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new LocationEditClickEvent(currentAddress));
            }
        });
        return rootBinding.getRoot();
    }

    public static class LocationEditClickEvent {
        private final Location location;

        public LocationEditClickEvent(Location address) {
            this.location = address;
        }

        public Location getLocation() {
            return location;
        }
    }
}

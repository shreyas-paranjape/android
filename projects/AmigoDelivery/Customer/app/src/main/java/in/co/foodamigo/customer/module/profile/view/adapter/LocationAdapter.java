package in.co.foodamigo.customer.module.profile.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import de.greenrobot.event.EventBus;
import in.co.foodamigo.customer.databinding.ItemLocationBinding;
import model.common.Location;

public class LocationAdapter extends ArrayAdapter<Location> {

    protected final LayoutInflater inflater;

    public LocationAdapter(Context context, int resource, List<Location> locationList) {
        super(context, resource, locationList);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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

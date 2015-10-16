package in.co.foodamigo.customer.module.profile.view.component;

import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.app.view.component.FormFragment;

import java.util.List;

public class AddressFormFragment extends FormFragment {
    private final String TAG = AddressFormFragment.class.getName();
    private EditText etAddress;
    private EditText etAddressLabel;
    private AutoCompleteTextView acLocality;
    private LocationManager mLocationManager;
    private Location lastKnown;

    private String[] localities = new String[]{"St.inez", "Taleigao", "Caranzalem", "Miramar"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationManager =
                (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        lastKnown = getLastKnownLocation();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.form_address, container, false);

        etAddress = (EditText) root.findViewById(R.id.etAddress);
        etAddressLabel = (EditText) root.findViewById(R.id.etAddressLabel);

        acLocality = (AutoCompleteTextView) root.findViewById(R.id.acLocality);
        acLocality.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.select_dialog_item, localities));
        acLocality.setThreshold(2);

        getActivity().setTitle("Address");
        showKeyboard();

        return root;
    }

    @Override
    protected void onSaveClick() {
        hideKeyboard();
        boolean done = true;
        StringBuilder messageBuilder = new StringBuilder("Enter : ");
        if ("".equals(etAddress.getText().toString())) {
            done = false;
            messageBuilder.append(" Address,");
        }
        if ("".equals(etAddressLabel.getText().toString())) {
            done = false;
            messageBuilder.append(" Address label,");
        }
        if ("".equals(acLocality.getText().toString())) {
            done = false;
            messageBuilder.append(" Localility,");
        }
        if (done) {
            getActivity().finish();
        } else {
            String message = messageBuilder.toString();
            if (message.endsWith(",")) {
                message = message.substring(0, message.length() - 1);
            }
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }

    }

    private Location getLastKnownLocation() {
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null
                    || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }
        if (bestLocation == null) {
            return null;
        }
        return bestLocation;
    }

}

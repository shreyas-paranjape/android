package in.co.foodamigo.customer.module.profile.view.component;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.util.LocationUtil;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.app.view.fragment.FormFragment;

public class AddressFormFragment extends FormFragment {
    private EditText etAddress;
    private AutoCompleteTextView acLocality;
    private Location lastKnown;

    private String[] localities = new String[]{"St.inez", "Taleigao", "Caranzalem", "Miramar"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lastKnown = LocationUtil.getLastKnownLocation(
                (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE)
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.form_address, container, false);
        getActivity().setTitle("Edit Address");
        initView(root);
        return root;
    }

    private void initView(View root) {
        etAddress = (EditText) root.findViewById(R.id.etAddress);
        acLocality = (AutoCompleteTextView) root.findViewById(R.id.acLocality);
        acLocality.setAdapter(new ArrayAdapter<>(getActivity(),
                android.R.layout.select_dialog_item, localities));
        acLocality.setThreshold(2);
    }

    @Override
    protected ValidationResult validate() {
        boolean done = true;
        String message = "";
        StringBuilder messageBuilder = new StringBuilder("Enter : ");
        if ("".equals(etAddress.getText().toString())) {
            done = false;
            messageBuilder.append(" Address,");
        }
        if ("".equals(acLocality.getText().toString())) {
            done = false;
            messageBuilder.append(" Locality,");
        }
        if (!done) {
            message = messageBuilder.toString();
            if (message.endsWith(",")) {
                message = message.substring(0, message.length() - 1);
            }
        }
        return new ValidationResult(done, message);
    }

    @Override
    protected void saveChanges() {
        getActivity().finish();
    }

}

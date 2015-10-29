package in.co.foodamigo.customer.module.profile.view.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.app.view.fragment.FormFragment;

public class PersonalDetailsFormFragment extends FormFragment {

    private EditText etName;
    private EditText etMobile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Edit Profile");
        View root = inflater.inflate(R.layout.form_profile, container, false);
        etName = (EditText) root.findViewById(R.id.etName);
        etMobile = (EditText) root.findViewById(R.id.etMobile);
        return root;
    }

    @Override
    protected ValidationResult validate() {
        boolean done = true;
        String message = "";
        StringBuilder messageBuilder = new StringBuilder("Enter : ");
        if ("".equals(etName.getText().toString())) {
            done = false;
            messageBuilder.append(" Name,");
        }
        if ("".equals(etMobile.getText().toString())) {
            done = false;
            messageBuilder.append(" Mobile,");
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

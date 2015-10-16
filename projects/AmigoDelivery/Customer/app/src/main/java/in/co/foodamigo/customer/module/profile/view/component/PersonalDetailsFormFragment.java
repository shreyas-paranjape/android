package in.co.foodamigo.customer.module.profile.view.component;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import in.co.foodamigo.customer.R;
import in.co.foodamigo.customer.module.app.view.component.FormFragment;

public class PersonalDetailsFormFragment extends FormFragment {

    private EditText etName;
    private EditText etMobile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.form_profile, container, false);
        etName = (EditText) root.findViewById(R.id.etName);
        etMobile = (EditText) root.findViewById(R.id.etName);
        return root;
    }

    @Override
    protected void onSaveClick() {
        getActivity().finish();
    }
}

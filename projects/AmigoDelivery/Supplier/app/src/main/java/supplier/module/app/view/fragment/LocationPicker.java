package supplier.module.app.view.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.util.Constant;

import java.io.Serializable;

import model.common.Location;
import supplier.R;

public class LocationPicker extends DialogFragment {

    private LocationSelectListener listener;

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listener = (LocationSelectListener)
                getArguments().getSerializable(Constant.PICKER_LISTENER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.view_pick_location, container, false);
        root.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    final Location location = new Location();
                    //TODO Attach address to location
                    location.setAddress("Bla Bla Bla");
                    listener.onLocationSelect(location);
                    dismiss();
                }
            }
        });
        return root;
    }

    public interface LocationSelectListener extends Serializable {

        void onLocationSelect(Location location);
    }
}

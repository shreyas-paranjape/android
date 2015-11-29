package com.view.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import com.util.Constant;

import java.io.Serializable;
import java.util.Calendar;

public class TimePickerFragment extends DialogFragment {

    private TimeSelectListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listener = (TimeSelectListener)
                getArguments().getSerializable(Constant.PICKER_LISTENER);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (listener != null) {
                    listener.onTimeSet(view, hourOfDay, minute);
                } else {
                    Log.w("TimePickerFragment", "Set a listener with key PICKER_LISTENER argument");
                }
            }
        }, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public interface TimeSelectListener extends Serializable {

        public void onTimeSet(TimePicker view, int hourOfDay, int minute);
    }
}

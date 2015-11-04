package com.example.gts.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by gts on 2/11/15.
 */
public class MyDialog extends DialogFragment implements View.OnClickListener {
    Button yes, no;
    TextView tv, plus, minus;
    private View view;
    int counter;

    public MyDialog() {
        counter = 0;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("My Dialog Title");
        View view = inflater.inflate(R.layout.my_dialog, null);
        yes = (Button) view.findViewById(R.id.yes);
        no = (Button) view.findViewById(R.id.No);
        tv = (TextView) view.findViewById(R.id.tvDialogue);
        plus = (TextView) view.findViewById(R.id.textPlus);
        minus = (TextView) view.findViewById(R.id.textMinus);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                tv.setText(String.valueOf(counter));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                if (counter < 0) {
                    counter = 0;
                }
                tv.setText(String.valueOf(counter));
            }
        });
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        setCancelable(false);
        return view;


    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.yes) {
            Toast.makeText(getActivity(), "Yes clicked", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getActivity(), "No button clicked", Toast.LENGTH_LONG).show();
        }

    }

    public String getCounter() {
        return String.valueOf(counter);
    }

}

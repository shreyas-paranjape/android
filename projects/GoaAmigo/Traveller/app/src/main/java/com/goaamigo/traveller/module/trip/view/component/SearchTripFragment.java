package com.goaamigo.traveller.module.trip.view.component;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.goaamigo.traveller.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import de.greenrobot.event.EventBus;

public class SearchTripFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {
    TextView textView1, TextView2;
    Button btn;
    private int Childern = 0;
    private int adult = 0;

    ImageView img1, img2;


    Spinner spnr, spnr2;
    String[] plants = new String[]{"1 Room", "2 Room", "3 Room", "4 Room"};
    String[] plants1 = new String[]{"2 Adults", "3 Adults", "4 Adults", "5 Adults"};


    MyDialog dialogObj = new MyDialog();
    TextView tvCounter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Search trips");
        View v = inflater.inflate(R.layout.fragment_search_trip, container, false);
        textView1 = (TextView) v.findViewById(R.id.textView1);
        TextView2 = (TextView) v.findViewById(R.id.textView2);
        img1 = (ImageView) v.findViewById(R.id.imageView2);
        img2 = (ImageView) v.findViewById(R.id.imageView3);
        spnr = (Spinner) v.findViewById(R.id.spinner);
        spnr2 = (Spinner) v.findViewById(R.id.spinner1);
        btn = (Button) v.findViewById(R.id.buttondialog);


        List<String> plantsList = new ArrayList<>(Arrays.asList(plants));
        // Initializing an ArrayAdapter
        plantsList = new ArrayList<>(Arrays.asList(plants));
        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, plantsList);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnr.setAdapter(spinnerArrayAdapter);


        List<String> plantsList1 = new ArrayList<>(Arrays.asList(plants1));
        // Initializing an ArrayAdapter
        plantsList1 = new ArrayList<>(Arrays.asList(plants1));
        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(
                getActivity(), R.layout.spinner_item, plantsList1);
        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinner_item);
        spnr2.setAdapter(spinnerArrayAdapter1);


        initView(v);
        return v;
    }


    private void initView(View v) {

        TextView2 = (TextView) v.findViewById(R.id.textView2);
        Button btnFindTrip = (Button) v.findViewById(R.id.btnFindTrip);
        final Calendar cal = Calendar.getInstance();
        int pYear = cal.get(Calendar.YEAR);
        int pMonth = cal.get(Calendar.MONTH);
        int pDay = cal.get(Calendar.DAY_OF_MONTH);

        //below code for todays date
        textView1.setText(new StringBuilder().append(pYear).append("").append("/").append(pMonth + 1).append("/").append(pDay));

        // Month is 0 based, just add 1
        btnFindTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FindTripButtonClickEvent());
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Tag Name", "Check in date");

                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        SearchTripFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                // dpd.setThemeDark(modeDarkDate.isChecked());
                //  dpd.vibrate(vibrateDate.isChecked());
                dpd.show(getFragmentManager(), "Datepickerdialog");


            }
        });


        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Tag Name", "Check out Date");
                final Calendar cal = Calendar.getInstance();
                int pYear = cal.get(Calendar.YEAR);
                int pMonth = cal.get(Calendar.MONTH);
                int pDay = cal.get(Calendar.DAY_OF_MONTH);
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        SearchTripFragment.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getFragmentManager();
                MyDialog myDialog = new MyDialog();
                myDialog.show(manager, "My Dialog");

            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int dayOfMonth, int monthOfYear, int year) {
        String date = +dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        textView1.setText(date);
    }

    public void onDateSet1(DatePickerDialog datePickerDialog, int dayOfMonth, int monthOfYear, int year) {
        String date = +dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        TextView2.setText(date);
    }

    public static class FindTripButtonClickEvent {

    }

    public class MyDialog extends DialogFragment implements View.OnClickListener {
        Button yes, no;
        TextView tv, plus, minus;
        private View view;
        private TextView childPlus, childMinus, childDisplay;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
            View view = inflater.inflate(R.layout.my_dialog, null);
            yes = (Button) view.findViewById(R.id.yes);
            no = (Button) view.findViewById(R.id.No);
            tv = (TextView) view.findViewById(R.id.tvDialogue);
            plus = (TextView) view.findViewById(R.id.textPlus);
            minus = (TextView) view.findViewById(R.id.textMinus);
            childPlus = (TextView) view.findViewById(R.id.textChildPlus);
            childMinus = (TextView) view.findViewById(R.id.textChildMinus);
            childDisplay = (TextView) view.findViewById(R.id.tvChild);
            childDisplay.setText(String.valueOf(Childern));
            tv.setText(String.valueOf(adult));


            childMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Childern--;
                    if (Childern < 0) {
                        Childern = 0;
                    }
                    childDisplay.setText(String.valueOf(Childern));
                }
            });
            childPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Childern++;
                    childDisplay.setText(String.valueOf(Childern));
                }
            });

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adult++;
                    tv.setText(String.valueOf(adult));
                }
            });
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adult--;
                    if (adult < 0) {
                        adult = 0;
                    }
                    tv.setText(String.valueOf(adult));
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
                dismiss();
            } else {
                Toast.makeText(getActivity(), "No button clicked", Toast.LENGTH_LONG).show();
                dismiss();
                adult = 0;
                Childern = 0;

            }

        }
    }
}





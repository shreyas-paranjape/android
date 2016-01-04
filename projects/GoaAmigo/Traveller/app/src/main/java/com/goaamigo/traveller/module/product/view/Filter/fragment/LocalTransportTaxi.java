package com.goaamigo.traveller.module.product.view.Filter.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.activity.ProductsActivity;
import com.view.fragment.AbstractFragment;

import de.greenrobot.event.EventBus;

public class LocalTransportTaxi extends AbstractFragment {
    private static final String[] locations = new String[] {
            "Mapusa", "panajim", "baga", "calangute", "vagator","vasco","margoa","ponda"
    };

    public LocalTransportTaxi() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_local_transport_taxi, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, locations);
        Button submit = (Button) v.findViewById(R.id.btnSubmit);
        final AutoCompleteTextView acPickUp = (AutoCompleteTextView)
                v.findViewById(R.id.acPickUpLocation);
        final AutoCompleteTextView acDropOff = (AutoCompleteTextView)
                v.findViewById(R.id.acDropOffLocation);
        final TextView tvPickUp = (TextView)v.findViewById(R.id.tvPickUpLocation);
        final TextView tvDropOff = (TextView) v.findViewById(R.id.tvDropOffLocation);
        acPickUp.setAdapter(adapter);
        acDropOff.setAdapter(adapter);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvPickUp.setText(acPickUp.getText().toString());
                tvDropOff.setText(acDropOff.getText().toString());
                EventBus.getDefault().post(new ProductsActivity.SlidingTabDismissEvent());
            }
        });
    }
}

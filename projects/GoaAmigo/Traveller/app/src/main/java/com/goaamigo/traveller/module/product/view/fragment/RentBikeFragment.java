package com.goaamigo.traveller.module.product.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.adapter.StringDialog;
import com.view.fragment.AbstractFragment;

public class RentBikeFragment extends AbstractFragment {
    public RentBikeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rent_bike, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        TextView termsAndCondition = (TextView)v.findViewById(R.id.tv_terms);
        termsAndCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringDialog terms = new StringDialog(R.string.terms);
                terms.show(getFragmentManager(),"dialog");
            }
        });
    }
}

package com.goaamigo.traveller.module.product.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appyvet.rangebar.RangeBar;
import com.goaamigo.traveller.R;
import com.view.fragment.AbstractFragment;

public class RestPrice extends AbstractFragment {

    public RestPrice() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_rest_price, container, false);
        initView(v);
        return v;
    }

    private void initView(View view) {
        final TextView price = (TextView) view.findViewById(R.id.tv);
        price.setText("Rs 499 & above");
        RangeBar priceRangeSlider = (RangeBar) view.findViewById(R.id.rangebar);
        priceRangeSlider.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                if (Integer.parseInt(leftPinValue) > 499 && Integer.parseInt(rightPinValue) < 9999) {
                    price.setText("Rs " + leftPinValue + " to " + rightPinValue);
                } else if (Integer.parseInt(rightPinValue) == 9999) {
                    price.setText("Rs " + leftPinValue + " & above");
                }
            }
        });
    }
}

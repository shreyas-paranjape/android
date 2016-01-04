package com.goaamigo.traveller.module.product.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appyvet.rangebar.RangeBar;
import com.cache.ObjectCache;
import com.goaamigo.traveller.R;
import com.goaamigo.traveller.module.product.view.adapter.ProductAdapter;
import com.util.Constant;
import com.util.IPredicate;
import com.view.fragment.AbstractFragment;
import com.view.model.Filter;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import model.catalogue.Product;

public class StayPrice extends AbstractFragment {

    protected List<Filter<Product>> filters = new ArrayList<>();

    public StayPrice() {
        Object cachedFilters = ObjectCache.get(Constant.PRODUCT);
        if (cachedFilters != null) {
            filters = (List<Filter<Product>>) cachedFilters;
        } else {
            filters = new ArrayList<>();
            ObjectCache.put(Constant.PRODUCT, filters);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_price, container, false);
        initView(view);
        return view;
    }
    private void addFilter(Filter filter) {
        filters.add(filter);
    }
    public void initView(View view) {
        final TextView price = (TextView) view.findViewById(R.id.tv);
        price.setText("Rs 499 & above");
        RangeBar priceRangeSlider = (RangeBar) view.findViewById(R.id.rangebar);
        priceRangeSlider.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                if (Integer.parseInt(leftPinValue) > 499 && Integer.parseInt(rightPinValue) < 9999) {
                    price.setText("Rs " + leftPinValue + " to " + rightPinValue);

                    EventBus.getDefault().post(new ProductAdapter.FilterEvent());
                } else if (Integer.parseInt(rightPinValue) == 9999) {
                    price.setText("Rs " + leftPinValue + " & above");
                }
            }
        });

    }
}

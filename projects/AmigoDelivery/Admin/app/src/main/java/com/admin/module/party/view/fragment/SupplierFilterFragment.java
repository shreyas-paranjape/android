package com.admin.module.party.view.fragment;

import android.view.View;

import com.util.Constant;
import com.view.fragment.AbstractFilterFragment;

import com.admin.R;
import model.party.Party;

public class SupplierFilterFragment extends AbstractFilterFragment<Party> {

    protected int getLayoutId() {
        return R.layout.layout_filter;
    }

    protected void initView(View root) {
    }

    protected String getArgumentKey() {
        return Constant.SUPPLIER;
    }

}

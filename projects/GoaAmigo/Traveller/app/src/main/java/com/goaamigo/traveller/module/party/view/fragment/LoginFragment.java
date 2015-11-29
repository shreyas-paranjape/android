package com.goaamigo.traveller.module.party.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goaamigo.traveller.R;
import com.view.fragment.AbstractFragment;

public class LoginFragment extends AbstractFragment {

    private String mAccountType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_login, container, false);
        initView(v);
        return v;
    }

    protected void initView(View v) {
        View vSignUp = v.findViewById(R.id.tvSignUp);
        vSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(SignUpFragment.class, getArguments(), true);
            }
        });
    }


}

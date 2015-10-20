package com.goaamigo.traveller.module.trip.view.component;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goaamigo.traveller.R;

public class SignUpFragment extends Fragment {

    public SignUpFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Sign Up");
        View v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        initView(v);
        return v;
    }
    private void initView(View v) {
    }
}

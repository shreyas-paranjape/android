package com.view.fragment;

import android.app.Fragment;
import android.os.Bundle;

import com.App;
import com.view.activity.AbstractActivity;

public abstract class AbstractFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof AbstractActivity)) {
            throw new RuntimeException("Abstract fragment can only be used " +
                    "with a host of type AbstractActivity");
        }
    }

    public App getApp() {
        return ((AbstractActivity) getActivity()).getApp();
    }
}

package com.view.fragment;

import android.app.Fragment;
import android.os.Bundle;

import com.App;
import com.event.ChangeContentEvent;
import com.view.activity.AppActivity;

import de.greenrobot.event.EventBus;

public abstract class AbstractFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof AppActivity)) {
            throw new RuntimeException("Abstract fragment can only be used " +
                    "with a host of type AbstractActivity");
        }
    }

    protected void show(Class<? extends Fragment> fragmentClass, Bundle data, boolean addToBackStack) {
        EventBus.getDefault().post(new ChangeContentEvent(fragmentClass, data, addToBackStack));
    }

    public App getApp() {
        return ((AppActivity) getActivity()).getApp();
    }
}

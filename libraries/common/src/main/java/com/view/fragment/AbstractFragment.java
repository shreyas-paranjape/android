package com.view.fragment;

import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v13.app.FragmentCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.App;
import com.event.ChangeContentEvent;
import com.view.activity.AppActivity;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public abstract class AbstractFragment extends Fragment {

    protected static final String TAG = AbstractFragment.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof AppActivity)) {
            throw new RuntimeException("Abstract fragment can only be used " +
                    "with a host of type AbstractActivity");
        }
    }

    public void askPermission(int requestId, String... permissions) {
        List<String> needPerms = new ArrayList<>();
        for (String permission : permissions) {
            if (!hasPermission(permission)) {
                needPerms.add(permission);
            }
        }
        FragmentCompat.requestPermissions(this,
                needPerms.toArray(new String[needPerms.size()]), requestId);
    }

    protected boolean hasPermission(String permission) {
        return !TextUtils.isEmpty(permission) &&
                PackageManager.PERMISSION_GRANTED ==
                        ContextCompat.checkSelfPermission(getActivity(), permission);
    }

    protected void show(Class<? extends Fragment> fragmentClass,
                        Bundle data, boolean addToBackStack) {
        EventBus.getDefault().post(new ChangeContentEvent(fragmentClass, data, addToBackStack));
    }

    public App getApp() {
        return ((AppActivity) getActivity()).getApp();
    }

}

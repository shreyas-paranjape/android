package com.admin.module.party.view.fragment;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.admin.R;
import com.view.fragment.AbstractAuthFragment;

import java.util.Arrays;


public class SignUpFragment extends AbstractAuthFragment {

    TelephonyManager telephonyManager;
    private EditText etUsername;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        telephonyManager = (TelephonyManager) getActivity()
                .getSystemService(Context.TELEPHONY_SERVICE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        Log.i(TAG, " requestCode: " + requestCode
                + "; Result : " + Arrays.toString(grantResults)
                + " ; Permissions: " + Arrays.toString(permissions));
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            etUsername.setText(telephonyManager.getLine1Number());
        }
    }


    protected int getLayoutId() {
        return R.layout.view_signup;
    }

    protected void initView(final View v) {
        final EditText etName = (EditText) v.findViewById(R.id.etName);
        etUsername = (EditText) v.findViewById(R.id.etUsername);
        //askPermission(999, Manifest.permission.READ_PHONE_STATE);
        v.findViewById(R.id.btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(etName.getText().toString(),
                        etUsername.getText().toString(),
                        getDeviceId());
            }
        });
    }

    private String getDeviceId() {
        String password = telephonyManager.getDeviceId();
        if (TextUtils.isEmpty(password)) {
            password = Settings.Secure
                    .getString(getActivity()
                            .getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return password;
    }

    @Override
    protected void onSuccess(Auth auth) {
        super.onSuccess(auth);
        getActivity().finish();
    }

    protected void onFailure(String message) {
        super.onFailure(message);
    }

}

package com.admin.module.party.view.fragment;

import android.view.View;
import android.widget.EditText;

import com.admin.R;
import com.view.fragment.AbstractAuthFragment;

public class LoginFragment extends AbstractAuthFragment {

    protected int getLayoutId() {
        return R.layout.view_login;
    }

    protected void initView(final View v) {
        v.findViewById(R.id.tvSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(SignUpFragment.class, getArguments(), true);
            }
        });
        final EditText etUsername = (EditText) v.findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) v.findViewById(R.id.etPassword);
        v.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(etUsername.getText().toString(), etPassword.getText().toString());
            }
        });
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

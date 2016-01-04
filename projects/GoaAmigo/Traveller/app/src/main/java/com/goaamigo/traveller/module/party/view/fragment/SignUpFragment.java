package com.goaamigo.traveller.module.party.view.fragment;

import android.view.View;
import android.widget.EditText;

import com.goaamigo.traveller.R;
import com.view.fragment.AbstractAuthFragment;

public class SignUpFragment extends AbstractAuthFragment {

    protected int getLayoutId() {
        return R.layout.view_signup;
    }

    protected void initView(final View v) {
        final EditText etUsername = (EditText) v.findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) v.findViewById(R.id.etPassword);
        v.findViewById(R.id.btnSignUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp("",etUsername.getText().toString(), etPassword.getText().toString());
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

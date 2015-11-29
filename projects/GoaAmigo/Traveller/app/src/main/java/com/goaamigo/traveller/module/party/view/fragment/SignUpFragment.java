package com.goaamigo.traveller.module.party.view.fragment;

import android.accounts.AccountManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goaamigo.traveller.R;
import com.view.activity.AbstractAuthActivity;
import com.view.fragment.AbstractFragment;

public class SignUpFragment extends AbstractFragment {

    private String mAccountType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mAccountType = getArguments().getString(AbstractAuthActivity.ARG_ACCOUNT_TYPE);
        View v = inflater.inflate(R.layout.view_signup, container, false);
        initView(v);
        return v;
    }

    protected void initView(View v) {
        initSubmitView(v);
    }

    private void initSubmitView(View v) {
        v.findViewById(R.id.btnSignUp)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userName = getEnteredUsername(v);
                        String password = getEnteredPassword(v);
                        String authToken = createNewAccount(userName, password);
                        onSubmitClick(userName, authToken, password);
                    }
                });
    }

    protected String createNewAccount(String userName, String password) {
        //TODO Call signUp and return new auth token
        return userName + password;
    }

    protected void onSubmitClick(String accountName, String authToken, String accountPassword) {
        Bundle data = new Bundle();
        data.putString(AccountManager.KEY_ACCOUNT_NAME, accountName);
        data.putString(AccountManager.KEY_ACCOUNT_TYPE, mAccountType);
        data.putString(AccountManager.KEY_AUTHTOKEN, authToken);
        data.putString(AbstractAuthActivity.PARAM_USER_PASS, accountPassword);
        //TODO returnToCaller(RESULT_OK, data);
    }

    private String getEnteredUsername(View v) {
        TextView tvUsername = (TextView) v.findViewById(R.id.etUsername);
        return tvUsername.getText().toString().trim();
    }

    private String getEnteredPassword(View v) {
        TextView tvPassword = (TextView) v.findViewById(R.id.etPassword);
        return tvPassword.getText().toString().trim();
    }
}

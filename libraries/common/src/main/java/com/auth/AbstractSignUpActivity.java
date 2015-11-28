package com.auth;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.view.activity.AbstractActivity;

public abstract class AbstractSignUpActivity extends AbstractActivity {

    private String mAccountType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccountType = getIntent().getStringExtra(AbstractAuthActivity.ARG_ACCOUNT_TYPE);
        initAlreadyMemberView();
        initSubmitView();
    }

    private void initAlreadyMemberView() {
        if (getAlreadyMemberViewId() != 0) {
            View vAlreadyMember = findViewById(getAlreadyMemberViewId());
            if (vAlreadyMember != null) {
                vAlreadyMember.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onAlreadyMemberClick();
                    }
                });
            } else {
                getApp().logWarn(AbstractAuthActivity.class.getName(), "initAlreadyMemberView",
                        "Already member view not found in the view");
            }
        } else {
            getApp().logWarn(AbstractAuthActivity.class.getName(), "initAlreadyMemberView",
                    "Override getAlreadyMemberViewId() and provide Already member view id");
        }
    }

    private void onAlreadyMemberClick() {
        returnToCaller(RESULT_CANCELED, null);
    }

    private void initSubmitView() {
        if (getSubmitViewId() != 0) {
            View vSubmit = findViewById(getSubmitViewId());
            if (vSubmit != null) {
                vSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userName = getEnteredUsername();
                        String password = getEnteredPassword();
                        String authToken = createNewAccount(userName, password);
                        onSubmitClick(userName, authToken, password);
                    }
                });
            } else {
                getApp().logWarn(AbstractAuthActivity.class.getName(), "initSubmitView",
                        "Already member view not found in the view");
            }
        } else {
            getApp().logWarn(AbstractAuthActivity.class.getName(), "initSubmitView",
                    "Override getAlreadyMemberViewId() and provide Already member view id");
        }
    }

    private String createNewAccount(String userName, String password) {
        //TODO Call signUp
        return userName + password;
    }

    private String getEnteredUsername() {
        String userName = "";
        TextView tvUsername = (TextView) findViewById(getUsernameTextViewId());
        if (tvUsername != null) {
            userName = tvUsername.getText().toString().trim();
        } else {
            getApp().logWarn(AbstractAuthActivity.class.getName(), "initSubmitView",
                    "Username text view not found in the view");
        }
        return userName;
    }

    private String getEnteredPassword() {
        String password = "";
        TextView tvPassword = (TextView) findViewById(getPasswordTextViewId());
        if (tvPassword != null) {
            password = tvPassword.getText().toString().trim();
        } else {
            getApp().logWarn(AbstractAuthActivity.class.getName(), "initSubmitView",
                    "Username text view not found in the view");
        }
        return password;
    }


    protected void onSubmitClick(String accountName, String authToken, String accountPassword) {
        Bundle data = new Bundle();
        data.putString(AccountManager.KEY_ACCOUNT_NAME, accountName);
        data.putString(AccountManager.KEY_ACCOUNT_TYPE, mAccountType);
        data.putString(AccountManager.KEY_AUTHTOKEN, authToken);
        data.putString(AbstractAuthActivity.PARAM_USER_PASS, accountPassword);
        returnToCaller(RESULT_OK, data);
    }

    protected void returnToCaller(int status, Bundle data) {
        final Intent res = new Intent();
        if (data != null) {
            res.putExtras(data);
        }
        setResult(status, res);
        finish();
    }

    protected abstract int getSubmitViewId();

    protected abstract int getAlreadyMemberViewId();

    protected abstract int getUsernameTextViewId();

    protected abstract int getPasswordTextViewId();
}

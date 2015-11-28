package com.auth;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.App;

public abstract class AbstractAuthActivity extends AccountAuthenticatorActivity {

    public final static String ARG_ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public final static String ARG_AUTH_TYPE = "AUTH_TYPE";
    public final static String ARG_ACCOUNT_NAME = "ACCOUNT_NAME";
    public final static String ARG_IS_ADDING_NEW_ACCOUNT = "IS_ADDING_ACCOUNT";
    public final static String PARAM_USER_PASS = "USER_PASS";
    public static final String TOKEN_TYPE_READ_ONLY = "Read only";
    public static final String TOKEN_TYPE_READ_ONLY_LABEL = "Read only access to an account";
    public static final String TOKEN_TYPE_FULL_ACCESS = "Full access";
    public static final String TOKEN_TYPE_FULL_ACCESS_LABEL = "Full access to an account";

    private final int REQ_SIGN_UP = 999;

    protected AccountManager mAccountManager;
    private String mAuthTokenType;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAccountManager = AccountManager.get(getBaseContext());
        mAuthTokenType = getIntent().getStringExtra(ARG_AUTH_TYPE);
        if (mAuthTokenType == null)
            mAuthTokenType = TOKEN_TYPE_FULL_ACCESS;
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            initSignUpView();
            View vSubmit = findViewById(getSubmitViewId());
            vSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    authenticate();
                }
            });
        } else {
            getApp().logWarn(
                    AbstractAuthActivity.class.getName(),
                    "onCreate",
                    "Override getLayoutId() to set layout");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_SIGN_UP && resultCode == RESULT_OK) {
            saveNewAccount(data);
        } else {
            getApp().logDebug(AbstractAuthActivity.class.getName(), "onActivityResult",
                    "Result not OK");
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public App getApp() {
        if (getApplication() instanceof App) {
            return (App) getApplication();
        } else {
            throw new RuntimeException("Please register a custom application class overriding com.App");
        }
    }

    private void initSignUpView() {
        if (getSignUpViewId() != 0) {
            View vSignUp = findViewById(getSignUpViewId());
            if (vSignUp != null) {
                vSignUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startSignUpActivity();
                    }
                });
            } else {
                getApp().logWarn(AbstractAuthActivity.class.getName(), "initSignUpView",
                        "SignUp view not found in the view");
            }
        } else {
            getApp().logWarn(AbstractAuthActivity.class.getName(), "initSignUpView",
                    "Override getSignUpViewId() and provide signUp view id");
        }
    }

    private void startSignUpActivity() {
        Intent signUpIntent = new Intent(getBaseContext(), getSignUpActivityClass());
        signUpIntent.putExtras(getIntent().getExtras());
        startActivityForResult(signUpIntent, REQ_SIGN_UP);
    }

    private void saveNewAccount(Intent intent) {
        getApp().logDebug(
                AbstractAuthActivity.class.getName(),
                "saveNewAccount",
                "Start");
        String accountName = intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        String accountPassword = intent.getStringExtra(PARAM_USER_PASS);
        final Account account = new Account(accountName,
                intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE));
        if (getIntent().getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, false)) {
            addAccountExplicitly(intent, accountPassword, account);
        } else {
            getApp().logDebug(
                    AbstractAuthActivity.class.getName(),
                    "saveNewAccount",
                    "setPassword");
            mAccountManager.setPassword(account, accountPassword);
        }
        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        finish();
        getApp().logDebug(
                AbstractAuthActivity.class.getName(),
                "saveNewAccount",
                "End");
    }

    private void addAccountExplicitly(Intent intent, String accountPassword, Account account) {
        getApp().logDebug(
                AbstractAuthActivity.class.getName(),
                "addAccountExplicitly",
                "Start");
        String authToken = intent.getStringExtra(AccountManager.KEY_AUTHTOKEN);
        String authTokenType = mAuthTokenType;
        mAccountManager.addAccountExplicitly(account, accountPassword, null);
        mAccountManager.setAuthToken(account, authTokenType, authToken);
        getApp().logDebug(
                AbstractAuthActivity.class.getName(),
                "addAccountExplicitly",
                "End");
    }

    private void authenticate() {
        getApp().logDebug(
                AbstractAuthActivity.class.getName(),
                "authenticate",
                "Start");
    }

    protected abstract int getSignUpViewId();

    protected abstract int getSubmitViewId();

    protected abstract int getLayoutId();

    protected abstract Class<? extends AbstractSignUpActivity> getSignUpActivityClass();

}

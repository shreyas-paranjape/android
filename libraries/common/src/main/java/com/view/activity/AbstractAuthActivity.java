package com.view.activity;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.App;
import com.event.ChangeContentEvent;

public abstract class AbstractAuthActivity
        extends AccountAuthenticatorActivity
        implements AppCompatCallback, AppActivity {

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
    private TextView tvTitle;

    private AppCompatDelegate delegate;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApp().registerListener(this);
        delegate = AppCompatDelegate.create(this, this);
        delegate.onCreate(savedInstanceState);
        mAccountManager = AccountManager.get(getBaseContext());
        mAuthTokenType = getIntent().getStringExtra(ARG_AUTH_TYPE);

        if (mAuthTokenType == null)
            mAuthTokenType = TOKEN_TYPE_FULL_ACCESS;
        if (getLayoutId() != 0) {
            delegate.setContentView(getLayoutId());
            setContentView(getLayoutId());
            setupToolbar();
            replaceContent(getInitContent());
            setTitle("");
        } else {
            getApp().logWarn(
                    AbstractAuthActivity.class.getName(),
                    "onCreate",
                    "Override getLayoutId() to set layout");
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onSupportActionModeStarted(ActionMode mode) {
        //let's leave this empty, for now
    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {
        // let's leave this empty, for now
    }

    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override
    protected void onDestroy() {
        getApp().unRegisterListener(this);
        super.onDestroy();
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

    public void authenticate() {
        getApp().logDebug(
                AbstractAuthActivity.class.getName(),
                "authenticate",
                "Start");
    }

    protected void setupToolbar() {
        int toolBarId = getToolbarId();
        if (toolBarId != 0) {
            Toolbar toolbar = (Toolbar) findViewById(toolBarId);
            if (toolbar != null) {
                toolbar.setTitleTextColor(Color.BLACK);
                if (getTitleId() != 0) {
                    tvTitle = (TextView) toolbar.findViewById(getTitleId());
                }
                delegate.setSupportActionBar(toolbar);
                ActionBar actionBar = delegate.getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setHomeButtonEnabled(true);
                }
            } else {
                getApp().logWarn(AbstractAuthActivity.class.getName(),
                        "setupToolbar",
                        "Given toolbar not part of layout hierarchy");
            }
        } else {
            getApp().logDebug(AbstractAuthActivity.class.getName(),
                    "setupToolbar",
                    "No toolbar for activity");
        }
    }

    protected void replaceContent(Fragment newFragment) {
        int containerId = getContentContainerId();
        if (containerId != 0 && newFragment != null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(containerId, newFragment, newFragment.getClass().getName())
                    .commit();
        }
    }

    protected void replaceContent(Fragment newFragment, boolean addToBackStack) {
        if (addToBackStack) {
            int containerId = getContentContainerId();
            if (containerId != 0 && newFragment != null) {
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(newFragment.getClass().getName())
                        .replace(containerId, newFragment, newFragment.getClass().getName())
                        .commit();
            }
        } else {
            replaceContent(newFragment);
        }

    }

    @Override
    public void setTitle(CharSequence title) {
        if (tvTitle != null) {
            delegate.setTitle("");
            tvTitle.setText(title);
        } else {
            delegate.setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEvent(ChangeContentEvent event) {
        if (Activity.class.isAssignableFrom(event.getContentClass())) {
            startNewActivity(event.getContentClass(), event.getData());
        } else if (Fragment.class.isAssignableFrom(event.getContentClass())) {
            replaceContent(event.getContentClass(), event.getData(), event.isAddToBackStack());
        }
    }

    protected void startNewActivity(Class clazz, Bundle data) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(data);
        startActivity(intent);
    }

    protected void replaceContent(Class newFragmentClass, Bundle data, boolean addToBackStack) {
        try {
            Fragment frag = (Fragment) newFragmentClass.newInstance();
            frag.setArguments(data);
            replaceContent(frag, addToBackStack);
        } catch (Exception e) {
            getApp().logError(AbstractActivity.class.getName(), "replaceContent",
                    "Got error: " + e.getMessage());
        }
    }

    protected int getLayoutId() {
        return 0;
    }

    protected int getToolbarId() {
        return 0;
    }

    protected Fragment getInitContent() {
        return null;
    }

    protected int getContentContainerId() {
        return 0;
    }

    protected int getTitleId() {
        return 0;
    }

}

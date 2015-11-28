package com.view.activity;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.App;
import com.event.ChangeContentEvent;
import com.view.fragment.NavigationDrawer;

import de.greenrobot.event.EventBus;

//@SuppressWarnings("unused")
public abstract class AbstractActivity extends AppCompatActivity {

    protected final EventBus eventBus = EventBus.getDefault();
    private TextView tvTitle;
    protected AccountManager accountManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerListener(this);
        accountManager = AccountManager.get(getBaseContext());
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            setupDrawer();
            setupToolbar();
            replaceContent(getInitContent(), true);
            setTitle("");
        } else {
            getApp().logWarn(AbstractActivity.class.getName(), "onCreate",
                    "Override getLayoutId() to set layout");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if (tvTitle != null) {
            super.setTitle("");
            tvTitle.setText(title);
        } else {
            super.setTitle(title);
        }
    }

    @Override
    protected void onDestroy() {
        unRegisterListener(this);
        super.onDestroy();
    }

    protected void replaceContent(Class newFragmentClass, Bundle data) {
        try {
            Fragment frag = (Fragment) newFragmentClass.newInstance();
            frag.setArguments(data);
            replaceContent(frag, true);
        } catch (Exception e) {
            getApp().logError(AbstractActivity.class.getName(), "replaceContent",
                    "Got error: " + e.getMessage());
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
                        .replace(containerId, newFragment, newFragment.getClass().getName())
                        .addToBackStack(newFragment.getClass().getName())
                        .commit();
            } else {
                if (newFragment == null) {
                    getApp().logWarn(AbstractActivity.class.getName(), "replaceContent",
                            "In replace content. newFragment is null");
                } else {
                    getApp().logWarn(AbstractActivity.class.getName(), "replaceContent",
                            "In replace content. Need a container Id to replace content");
                }

            }
        } else {
            replaceContent(newFragment);
        }
    }

    protected void startNewActivity(Class clazz) {
        startNewActivity(clazz, new Bundle());
    }

    protected void startNewActivity(Class clazz, Bundle data) {
        Intent intent = new Intent(AbstractActivity.this, clazz);
        intent.putExtras(data);
        startActivity(intent);
    }

    protected void setupDrawer() {
        int drawerId = getDrawerFragmentId();
        if (drawerId != 0) {
            eventBus.post(
                    new NavigationDrawer.SetupDrawerEvent(
                            getDrawerLayout(),
                            drawerId));
        }
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
                setSupportActionBar(toolbar);
                ActionBar actionBar = getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayHomeAsUpEnabled(true);
                    actionBar.setHomeButtonEnabled(true);
                }
            } else {
                getApp().logWarn(AbstractActivity.class.getName(),
                        "setupToolbar",
                        "Given toolbar not part of layout hierarchy");
            }
        } else {
            getApp().logDebug(AbstractActivity.class.getName(),
                    "setupToolbar",
                    "No toolbar for activity");
        }
    }

    protected void registerListenerSticky(Object... listeners) {
        getApp().registerListener(listeners);
    }

    protected void unRegisterListener(Object... listeners) {
        getApp().unRegisterListener(listeners);
    }

    protected void registerListener(Object... listeners) {
        getApp().registerListener(listeners);
    }

    public App getApp() {
        if (getApplication() instanceof App) {
            return (App) getApplication();
        } else {
            throw new RuntimeException("Please register a custom application class by overriding com.App");
        }
    }

    public void signUp(String tokenType) {
        final AccountManagerFuture<Bundle> future =
                accountManager.addAccount(getApp().getAccountType(),
                        tokenType, null, null, this,
                        new AccountManagerCallback<Bundle>() {
                            @Override
                            public void run(AccountManagerFuture<Bundle> future) {
                                try {
                                    future.getResult();
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }, null);
    }

    public String getAuthToken() {
        Bundle accountBundle = getAccountBundle();
        if (null != accountBundle) {
            return accountBundle.getString(AccountManager.KEY_AUTHTOKEN);
        } else {
            return null;
        }
    }

    public void invalidateAuthToken() {
        String authToken = getAuthToken();
        if (authToken != null) {
            accountManager.invalidateAuthToken(getApp().getAccountType(), authToken);
        } else {
            getApp().logWarn(
                    AbstractActivity.class.getName(),
                    "invalidateAuthToken",
                    "Account not found");
        }
    }

    public Bundle getAccountBundle() {
        if (getApp().getAccountType() == null) {
            throw new RuntimeException("Account type is null");
        } else {
            final Account availableAccounts[] = accountManager.getAccountsByType(getApp().getAccountType());
            if (availableAccounts.length == 1) {
                final AccountManagerFuture<Bundle> future =
                        accountManager.getAuthToken(availableAccounts[0], getApp().getAccountType(),
                                null, this, null, null);
                try {
                    return future.getResult();
                } catch (Exception e) {
                    getApp().logError(
                            AbstractActivity.class.getName(),
                            "getAuthToken",
                            e.getMessage());
                    return null;
                }
            } else {
                return null;
            }
        }
    }

    public void onEvent(ChangeContentEvent event) {
        if (Activity.class.isAssignableFrom(event.getContentClass())) {
            startNewActivity(event.getContentClass(), event.getData());
        } else if (Fragment.class.isAssignableFrom(event.getContentClass())) {
            replaceContent(event.getContentClass(), event.getData());
        }
    }

    protected int getLayoutId() {
        return 0;
    }

    protected int getDrawerFragmentId() {
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

    protected DrawerLayout getDrawerLayout() {
        return null;
    }

    protected int getTitleId() {
        return 0;
    }

}

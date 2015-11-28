package com.auth;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import static com.auth.AbstractAuthActivity.ARG_ACCOUNT_NAME;
import static com.auth.AbstractAuthActivity.ARG_ACCOUNT_TYPE;
import static com.auth.AbstractAuthActivity.ARG_AUTH_TYPE;
import static com.auth.AbstractAuthActivity.ARG_IS_ADDING_NEW_ACCOUNT;
import static com.auth.AbstractAuthActivity.TOKEN_TYPE_FULL_ACCESS;
import static com.auth.AbstractAuthActivity.TOKEN_TYPE_FULL_ACCESS_LABEL;
import static com.auth.AbstractAuthActivity.TOKEN_TYPE_READ_ONLY;
import static com.auth.AbstractAuthActivity.TOKEN_TYPE_READ_ONLY_LABEL;

@SuppressWarnings("unused")
public class AbstractAuthenticator extends AbstractAccountAuthenticator {

    private static final String TAG = AbstractAuthenticator.class.getName();
    private static final String INVALID_AUTH_TYPE = "invalid authTokenType";
    private final Context mContext;

    public AbstractAuthenticator(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType,
                             String authTokenType, String[] requiredFeatures, Bundle options)
            throws NetworkErrorException {
        final Intent intent = new Intent(mContext, AbstractAuthActivity.class);
        intent.putExtra(ARG_ACCOUNT_TYPE, accountType);
        intent.putExtra(ARG_AUTH_TYPE, authTokenType);
        intent.putExtra(ARG_IS_ADDING_NEW_ACCOUNT, true);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account,
                               String authTokenType, Bundle options)
            throws NetworkErrorException {
        final Bundle result = new Bundle();
        if (isTokenTypeSupported(authTokenType)) {
            putError(result, INVALID_AUTH_TYPE);
        } else {
            final AccountManager am = AccountManager.get(mContext);
            String authToken = am.peekAuthToken(account, authTokenType);
            if (!TextUtils.isEmpty(authToken)) {
                putAuthParams(account, result, authToken);
            } else {
                authToken = reAuthenticate(account, authTokenType, am);
                if (TextUtils.isEmpty(authToken)) {
                    putRePromptIntent(result, response, account, authTokenType);
                } else {
                    putAuthParams(account, result, authToken);
                }
            }
        }
        return result;
    }

    private void putError(Bundle result, String errorMessage) {
        result.putString(AccountManager.KEY_ERROR_MESSAGE, errorMessage);
    }

    private void putAuthParams(Account account, Bundle result, String authToken) {
        result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
        result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
        result.putString(AccountManager.KEY_AUTHTOKEN, authToken);
    }

    private void putRePromptIntent(Bundle result, AccountAuthenticatorResponse response,
                                   Account account, String authTokenType) {
        final Intent intent = new Intent(mContext, AbstractAuthActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        intent.putExtra(ARG_ACCOUNT_TYPE, account.type);
        intent.putExtra(ARG_AUTH_TYPE, authTokenType);
        intent.putExtra(ARG_ACCOUNT_NAME, account.name);
        result.putParcelable(AccountManager.KEY_INTENT, intent);
    }

    @Nullable
    private String reAuthenticate(Account account, String authTokenType, AccountManager am) {
        final String password = am.getPassword(account);
        if (password != null) {
            //TODO return sServerAuthenticate.userSignIn(account.name, password, authTokenType);
            return "";
        } else {
            return "";
        }
    }

    private boolean isTokenTypeSupported(String authTokenType) {
        return !authTokenType.equals(TOKEN_TYPE_READ_ONLY)
                && !authTokenType.equals(TOKEN_TYPE_FULL_ACCESS);
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        if (TOKEN_TYPE_FULL_ACCESS.equals(authTokenType))
            return TOKEN_TYPE_FULL_ACCESS_LABEL;
        else if (TOKEN_TYPE_READ_ONLY.equals(authTokenType))
            return TOKEN_TYPE_READ_ONLY_LABEL;
        else
            return authTokenType + " (Label)";
    }


    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response,
                              Account account, String[] features)
            throws NetworkErrorException {
        final Bundle result = new Bundle();
        result.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, false);
        return result;
    }


    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response,
                                     Account account, Bundle options)
            throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response,
                                    Account account, String authTokenType, Bundle options)
            throws NetworkErrorException {
        return null;
    }


}

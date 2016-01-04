package com.android.volley.toolbox;

import android.app.Application;

import com.android.volley.AuthFailureError;

public class SharedPrefAuthenticator implements Authenticator {
    private Application app;

    public SharedPrefAuthenticator(Application app) {

    }

    @Override
    public String getAuthToken() throws AuthFailureError {
        return null;
    }

    @Override
    public void invalidateAuthToken(String authToken) {

    }
}

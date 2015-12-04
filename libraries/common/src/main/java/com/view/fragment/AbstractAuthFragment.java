package com.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;
import com.util.Constant;
import com.view.activity.AppActivity;

import rest.RestResource;

@SuppressWarnings("unused")
public class AbstractAuthFragment extends AbstractFragment {

    private static final int AUTH_REQUEST_ID = 99;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApp().registerListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutId() != 0) {
            View v = inflater.inflate(getLayoutId(), container, false);
            initView(v);
            return v;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected void initView(View v) {
    }

    protected int getLayoutId() {
        return 0;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        getApp().unRegisterListener(this);
    }


    protected void dummyLogin(String userName, String password) {
        Auth auth = new Auth("", "", "", "", userName, password, "password");
        auth.setAccess_token("123456789");
        onSuccess(auth);
    }

    protected void login(String userName, String password) {
        sendAuthRequest(null, null, userName, password, Constant.CONTEXT_TOKEN);
    }

    protected void login(String userName, String password, String secret) {
        sendAuthRequest(null, null, userName, password, Constant.CONTEXT_TOKEN, secret);
    }

    protected void signUp(String name, String userName, String password) {
        sendAuthRequest(name, getIdType(userName), userName, password, Constant.CONTEXT_SIGNUP);
    }

    protected void signUp(String name, String userName, String password, String secret) {
        sendAuthRequest(name, getIdType(userName), userName, password, Constant.CONTEXT_SIGNUP, secret);
    }

    private void sendAuthRequest(String name, String idType,
                                 String userName, String password,
                                 String context) {
        Auth authRequest = getAuth(name, idType, userName, password);
        new RestResource<Auth>(getApp().getBaseUri() + context)
                .postAsync(
                        AUTH_REQUEST_ID,
                        getApp().getQueue(),
                        new TypeToken<Auth>() {
                        }.getType(),
                        authRequest);
    }

    private void sendAuthRequest(String name, String idType,
                                 String userName, String password,
                                 String context, String secret) {
        Auth authRequest = getAuth(name, idType, userName, password);
        new RestResource<Auth>(getApp().getBaseUri() + context)
                .postAsync(
                        AUTH_REQUEST_ID,
                        getApp().getQueue(),
                        new TypeToken<Auth>() {
                        }.getType(),
                        authRequest,
                        secret);
    }


    @NonNull
    protected Auth getAuth(String name, String idType, String userName, String password) {
        String[] appCredentials = getApp().getAppCredentials();
        return new Auth(appCredentials[0], appCredentials[1], name, idType,
                userName, password, Constant.GRANT_TYPE_PASSWORD);
    }

    @SuppressWarnings("unused")
    public void onEvent(RestResource.HttpRequestComplete<?> event) {
        if (null != event.getResponse() && event.getResponse() instanceof Auth) {
            onSuccess((Auth) event.getResponse());
        } else {
            onFailure(event.getMessage());
        }
    }

    protected void onSuccess(Auth auth) {
        ((AppActivity) getActivity()).getApp().saveToPref(Constant.KEY_USER_TOKEN, auth.getAccess_token());
        ((AppActivity) getActivity()).getApp().saveToPref(Constant.KEY_USER_ROL, auth.getRol());
    }

    protected void onFailure(String message) {
        getApp().logError(AbstractAuthFragment.class.getName(), "onFailure", message);
    }

    protected String getIdType(String s) {
        if (isValidEmail(s)) {
            return "email";
        } else if (isValidMobile(s)) {
            return "mobile";
        } else {
            throw new RuntimeException("Invalid username");
        }
    }

    private boolean isValidEmail(CharSequence target) {
        return target != null &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private boolean isValidMobile(CharSequence target) {
        return target != null &&
                Patterns.PHONE.matcher(target).matches();
    }

    private boolean isEmail(String s) {
        return true;
    }

    protected class Auth {
        private final String name;
        private final String client_id;
        private final String client_secret;
        private final String username;
        private final String password;
        private final String grant_type;
        private final String idType;
        private String access_token;
        private String rol;

        private Auth(String client_id, String client_secret, String name,
                     String idType, String username, String password, String grant_type) {
            this.name = name;
            this.client_id = client_id;
            this.client_secret = client_secret;
            this.idType = idType;
            this.username = username;
            this.password = password;
            this.grant_type = grant_type;
        }

        @Override
        public String toString() {
            return "Auth{" +
                    "client_id='" + client_id + '\'' +
                    ", client_secret='" + client_secret + '\'' +
                    ", idType='" + idType + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", grant_type='" + grant_type + '\'' +
                    ", access_token='" + access_token + '\'' +
                    ", rol='" + rol + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }


        public String getAccess_token() {
            return access_token;
        }

        @SuppressWarnings("unused")
        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getRol() {
            return rol;
        }

        @SuppressWarnings("unused")
        public void setRol(String rol) {
            this.rol = rol;
        }

        public String getIdType() {
            return idType;
        }
    }
}

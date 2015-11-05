package com.goaamigo.traveller.module.trip.view.component;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.goaamigo.traveller.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import de.greenrobot.event.EventBus;

//////////////////////////////////////////////////////////////////////////////////////////////////////////
/////      FOR GOOGLE SIGN UP google_service.json NEED TO BE ADDED AND NEED AN API KEY TO WORK      //////
//////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////////////
/////                       FOR FACEBOOK SIGN UP NEED NEW APP ID TO WORK                            //////
//////////////////////////////////////////////////////////////////////////////////////////////////////////

public class LoginFragment extends Fragment implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {
    EditText username;
    EditText password;
    TextView forgot;
    Button Login;
    Button singUp;

    private static final int RC_SIGN_IN = 0;
    private GoogleApiClient mGoogleApiClient;
    String personName;
    String personPhoto;
    String personGooglePlusProfile;
    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("LogIn");
        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();

        View v = inflater.inflate(R.layout.fragment_login, container, false);
        loginButton = (LoginButton) v.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });
        // If using in a fragment
        initView(v);
        return v;

    }

    private void initView(View v) {
        username = (EditText) v.findViewById(R.id.etbUsername);
        password = (EditText) v.findViewById(R.id.etxPassword);
        forgot = (TextView) v.findViewById(R.id.tvForgot);
        String uName = username.getText().toString();
        String pass = password.getText().toString();

        Login = (Button) v.findViewById(R.id.btnLogin);
        singUp = (Button) v.findViewById(R.id.btnSignUp);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new FindLoginButtonClickEvent());
            }
        });
        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new FindSignUpButtonClickEvent());
            }
        });
        v.findViewById(R.id.sign_in_button).setOnClickListener(this);
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .addScope(new Scope(Scopes.EMAIL))
                .build();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(mIsResolving){
            mGoogleApiClient.connect();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        if(mIsResolving){
            mGoogleApiClient.connect();
        }
    }



    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }
    private void onSignInClicked() {
        mGoogleApiClient.connect();
    }
    @Override
    public void onConnected(Bundle bundle) {
        mShouldResolve = false;
        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
            Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            personName = currentPerson.getDisplayName();
            personPhoto = currentPerson.getImage().getUrl();
            personGooglePlusProfile = currentPerson.getUrl();
        }
        Log.i("Google data","personName :" + personName + "personPhotoUrl" +personPhoto + "profile" + personGooglePlusProfile);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_in_button:
                onSignInClicked();
                break;
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(getActivity(), RC_SIGN_IN);
                mIsResolving = true;
            } catch (IntentSender.SendIntentException e) {
                Log.e("onConnectionFailed", "Could not resolve ConnectionResult.", e);
                mIsResolving = false;

                mGoogleApiClient.connect();
            }
        } else {
            // Could not resolve the connection result, show the user an
            // error dialog.
            showErrorDialog(connectionResult);

        }
    }
    private void showErrorDialog(ConnectionResult connectionResult) {
        Log.d("showErrorDialog", "1");
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(getActivity());

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(getActivity(), resultCode, RC_SIGN_IN,
                        new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                mShouldResolve = false;

                            }
                        }).show();
            } else {
                Log.d("showErrorDialog", "Google Play Services Error:" + connectionResult);
                String errorString = apiAvailability.getErrorString(resultCode);
                Toast.makeText(getActivity(), errorString, Toast.LENGTH_SHORT).show();
                mShouldResolve = false;
            }
        }
    }
    class FindLoginButtonClickEvent {
    }

    class FindSignUpButtonClickEvent {
    }
}

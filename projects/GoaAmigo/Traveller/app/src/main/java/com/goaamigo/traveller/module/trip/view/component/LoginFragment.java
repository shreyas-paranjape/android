package com.goaamigo.traveller.module.trip.view.component;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.goaamigo.traveller.R;
import com.util.StringConstants;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import de.greenrobot.event.EventBus;

public class LoginFragment extends Fragment {
    EditText username;
    EditText password;
    TextView forgot;
    Button Login;
    Button singUp;
    Button facebook;
    Button googlePlus;

    Boolean userPassMatched = false;
    private android.util.Log log;

    public LoginFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("LogIn");
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        initView(v);
        return v;

    }

    private void initView(View v) {
        username = (EditText)v.findViewById(R.id.etbUsername);
        password = (EditText)v.findViewById(R.id.etxPassword);
        forgot = (TextView)v.findViewById(R.id.tvForgot);
        String uName = username.getText().toString();
        String pass = password.getText().toString();
        final RequestQueue queue = Volley.newRequestQueue(getActivity());
        final String uri = new String("https://192.168.10.252:8443/google");

        Login = (Button)v.findViewById(R.id.btnLogin);
        singUp = (Button)v.findViewById(R.id.btnSignUp);
        facebook = (Button)v.findViewById(R.id.btnFacebook);
        googlePlus = (Button)v.findViewById(R.id.btnGooglePlus);
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
        googlePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    class FindLoginButtonClickEvent {
    }
    class FindSignUpButtonClickEvent {
    }
}

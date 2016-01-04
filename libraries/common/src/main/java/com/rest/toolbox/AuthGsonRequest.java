package com.rest.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class AuthGsonRequest<T> extends EncodedGsonRequest<T> {

    private Map<String, String> headers = new HashMap<>();

    public AuthGsonRequest(int method, String url, T requestBody,
                           Response.Listener<T> listener,
                           Response.ErrorListener errorListener,
                           Type type, String secret, String token) {
        super(method, url, requestBody, listener, errorListener, type, secret);
        headers.put("Authorization", "Bearer " + token);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers;
    }
}

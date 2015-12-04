package rest.common;

import com.android.volley.Cache;
import com.android.volley.Response;
import com.android.volley.toolbox.Crypt;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

public class EncodedGsonRequest<T> extends GsonRequest<T> {

    private final String secret;

    public EncodedGsonRequest(int method, String url, T requestBody,
                              Response.Listener<T> listener,
                              Response.ErrorListener errorListener, Type type, String secret) {
        super(method, url, requestBody, listener, errorListener, type);
        this.secret = secret;
        String data = gson.toJson(requestBody);
        String payload = "{\"data\":" + data + "}";
        if (requestBody != null) {
            mRequestBody = Crypt.encrypt(payload, secret);
        }
    }

    @Override
    protected Response<T> getResponse(String responseString, Cache.Entry cacheHeaders)
            throws UnsupportedEncodingException {
        String decoded = Crypt.decrypt(responseString, secret);
        return super.getResponse(decoded, cacheHeaders);
    }
}

package rest.common;

import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

public class GsonRequest<T> extends JsonRequest<T> {

    protected final Gson gson;
    protected final Type type;
    protected String mRequestBody;

    public GsonRequest(int method, String url, T requestBody,
                       Response.Listener<T> listener,
                       Response.ErrorListener errorListener, Type type) {
        super(method, url, null, listener, errorListener);
        this.type = type;
        gson = new GsonBuilder().create();
        if (requestBody != null) {
            mRequestBody = gson.toJson(requestBody);
        }
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return getResponse(json, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @SuppressWarnings("unchecked")
    protected Response<T> getResponse(String responseString, Cache.Entry cacheHeaders)
            throws UnsupportedEncodingException {
        return (Response<T>) Response.success(gson.fromJson(responseString, type), cacheHeaders);
    }

    @Override
    public byte[] getBody() {
        try {
            Log.d("GsonRequest", gson.toJson(mRequestBody));
            return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                    mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }
}

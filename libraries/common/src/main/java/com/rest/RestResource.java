package com.rest;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.orm.SugarRecord;
import com.rest.toolbox.EncodedGsonRequest;
import com.rest.toolbox.GsonRequest;

import java.lang.reflect.Type;
import java.util.List;

import de.greenrobot.event.EventBus;

@SuppressWarnings("unused")
public class RestResource<T> {

    private static final String TAG = RestResource.class.getName();
    private static final long REQUEST_TIMEOUT = 5;

    private final String uri;

    public RestResource(String uri) {
        this.uri = uri;
    }

    public void getAllAndPersist(final int requestId, RequestQueue queue, Type type) {
        queue.add(new GsonRequest<>(
                Request.Method.GET,
                uri,
                null,
                new Response.Listener<List<T>>() {
                    @Override
                    public void onResponse(List<T> response) {
                        boolean success = true;
                        StringBuilder errMessage = new StringBuilder("Persist error : ");
                        for (T t : response) {
                            try {
                                SugarRecord.save(t);
                            } catch (Throwable th) {
                                Log.e(TAG, "Persist error: Entity: " + t + " Error : " + th);
                                success = false;
                                errMessage.append("Entity: ")
                                        .append(t)
                                        .append(" Error: ")
                                        .append(th.getMessage());
                            }
                        }
                        EventBus.getDefault()
                                .post(new HttpRequestComplete<>(requestId,
                                        success,
                                        success ? null : errMessage.toString(),
                                        response));
                    }
                },
                newErrorListener(requestId),
                type));
    }

//    public T postSync(final int requestId, RequestQueue queue, Type type, T body) {
//        RequestFuture<T> future = RequestFuture.newFuture();
//        queue.add(new GsonRequest<>(
//                Request.Method.POST,
//                uri,
//                body,
//                future,
//                future,
//                type));
//        try {
//            return future.get(REQUEST_TIMEOUT, TimeUnit.SECONDS);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//            //EventBus.getDefault().post(new HttpRequestComplete(requestId, false, e.getMessage()));
//            //return null;
//        }
//    }

    public void postAsync(final int requestId, RequestQueue queue, Type type, T body) {
        Log.e(TAG, "Req : " + body);
        queue.add(new GsonRequest<>(
                Request.Method.POST,
                uri,
                body,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        Log.e(TAG, "Res" + response);
                        EventBus.getDefault().post(
                                new HttpRequestComplete<>(requestId, true, null, response));
                    }
                },
                newErrorListener(requestId),
                type));
    }

    public void postAsync(final int requestId, RequestQueue queue, Type type, T body, String secret) {
        Log.e(TAG, "Req : " + body);
        queue.add(new EncodedGsonRequest<>(
                Request.Method.POST,
                uri,
                body,
                new Response.Listener<T>() {
                    @Override
                    public void onResponse(T response) {
                        Log.e(TAG, "Res" + response);
                        EventBus.getDefault().post(
                                new HttpRequestComplete<>(requestId, true, null, response));
                    }
                },
                newErrorListener(requestId),
                type,
                secret));
    }

    private Response.ErrorListener newErrorListener(final int requestId) {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                EventBus.getDefault().post(
                        new HttpRequestComplete<T>(requestId, false, error.getMessage(), null));
            }
        };
    }


    public static class HttpRequestComplete<T> {
        private final int requestId;
        private final boolean successful;
        private final String message;
        private final T response;

        public HttpRequestComplete(int requestId, boolean success, String message, T response) {
            this.requestId = requestId;
            this.successful = success;
            this.message = message;
            this.response = response;
        }

        @Override
        public String toString() {
            return "HttpRequestComplete{" +
                    "requestId=" + requestId +
                    ", response =" + response +
                    ", successful=" + successful +
                    ", message='" + message + '\'' +
                    '}';
        }


        public boolean isSuccessful() {
            return successful;
        }

        public String getMessage() {
            return message;
        }

        public int getRequestId() {
            return requestId;
        }

        public T getResponse() {
            return response;
        }
    }
}

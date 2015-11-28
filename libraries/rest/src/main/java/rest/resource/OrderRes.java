package rest.resource;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import model.order.Order;
import rest.common.GsonRequest;
import rest.common.IResource;

public class OrderRes implements IResource {

    private final RequestQueue queue;
    private static final String ORDER = "/order";

    public OrderRes(RequestQueue queue) {
        this.queue = queue;
    }

    public void placeOrder(Order order) {
        queue.add(new GsonRequest<>(
                Request.Method.POST,
                dataBaseUri + ORDER,
                order,
                new Response.Listener<Order>() {
                    @Override
                    public void onResponse(Order response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Orders", "error" + error.getMessage());
                    }
                },
                new TypeToken<Order>() {
                }.getType()));
    }
}

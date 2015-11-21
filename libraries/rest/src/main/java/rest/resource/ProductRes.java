package rest.resource;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;

import java.util.Arrays;
import java.util.List;

import model.catalogue.Product;
import model.catalogue.ProductCategory;
import model.catalogue.ProductDetail;
import rest.common.GsonRequest;
import rest.common.IResource;

public class ProductRes implements IResource {

    private final RequestQueue queue;

    public ProductRes(RequestQueue queue) {
        this.queue = queue;
    }

    private static final String path = "/product";

    public void fetchAndPersist() {
        queue.add(new GsonRequest<>(
                Request.Method.GET,
                baseUri + "/category",
                null,
                new Response.Listener<List<ProductCategory>>() {
                    @Override
                    public void onResponse(List<ProductCategory> response) {
                        Log.d("Product Categories", Arrays.toString(response.toArray()));
                        for (ProductCategory productCategory : response) {
                            SugarRecord.save(productCategory);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Product Categories", error.getMessage());
                    }
                },
                new TypeToken<List<ProductCategory>>() {
                }.getType()));

        queue.add(new GsonRequest<>(
                Request.Method.GET,
                baseUri + path,
                null,
                new Response.Listener<List<Product>>() {
                    @Override
                    public void onResponse(List<Product> response) {
                        for (Product product : response) {
                            Log.d("ProductRes", product.toString());
                            ProductDetail detail = product.getDetail();
                            if (detail != null) {
                                detail.setId(product.getId());
                                SugarRecord.save(product.getDetail());
                            }
                            SugarRecord.save(product);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                },
                new TypeToken<List<Product>>() {
                }.getType()));

    }

}

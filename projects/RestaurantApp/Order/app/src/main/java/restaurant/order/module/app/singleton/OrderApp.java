package restaurant.order.module.app.singleton;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.order.CurrentOrderManager;
import com.orm.SugarContext;
import com.util.FontsOverride;

import model.DummyData;
import rest.resource.ProductRes;


public class OrderApp extends Application {

    private final CurrentOrderManager currentOrderManager = new CurrentOrderManager();
    private RequestQueue queue;// = Volley.newRequestQueue(getApplicationContext());
    private ProductRes productRes;// = new ProductRes(queue);

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
        productRes = new ProductRes(queue);
        SugarContext.init(getApplicationContext());
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Raleway-Thin.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "Raleway-Regular.ttf");
        productRes.fetchAndPersist();
        // DummyData.createDummyData("rest.order");

    }

    @Override
    public void onTerminate() {
        SugarContext.terminate();
        super.onTerminate();
    }

    public CurrentOrderManager getCurrentOrderManager() {
        return currentOrderManager;
    }


}
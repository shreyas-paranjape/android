package restaurant.order.module.app.singleton;

import android.app.Application;
import android.support.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.order.CurrentOrderManager;
import com.orm.SugarContext;
import com.util.FontsOverride;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import model.catalogue.Product;
import model.order.Order;
import model.order.OrderItem;
import model.party.Party;
import rest.resource.OrderRes;
import rest.resource.ProductRes;


public class OrderApp extends Application {

    private final CurrentOrderManager currentOrderManager = new CurrentOrderManager();
    private RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
        ProductRes productRes = new ProductRes(queue);
        SugarContext.init(getApplicationContext());
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Raleway-Thin.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "Raleway-Regular.ttf");
        productRes.fetchAndPersist();
        Order o = getOrder();
        new OrderRes(queue).placeOrder(o);
        // DummyData.createDummyData("rest.order");
    }

    @NonNull
    private Order getOrder() {
        final Order o = new Order();
        final Party party = new Party();
        final List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(new Product(), 500, 1, "placed"));
        party.setId(5);
        o.setCode(UUID.randomUUID().toString());
        o.setStatus("placed");
        o.setParty(party);
        o.setOrderItems(orderItems);
        return o;
    }

    @Override
    public void onTerminate() {
        SugarContext.terminate();
        super.onTerminate();
    }

    public RequestQueue getQueue() {
        return queue;
    }

    public CurrentOrderManager getCurrentOrderManager() {
        return currentOrderManager;
    }

}
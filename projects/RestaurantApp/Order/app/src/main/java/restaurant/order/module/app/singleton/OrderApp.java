package restaurant.order.module.app.singleton;

import android.support.annotation.NonNull;

import com.App;


public class OrderApp extends App {

    private static final String baseUri = "http://192.168.10.250:3000";
    private static final String TAG = OrderApp.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @NonNull
    @Override
    protected String getRegularFontAssetName() {
        return "Raleway-Regular.ttf";
    }

    @NonNull
    @Override
    protected String getThinFontAssetName() {
        return "Raleway-Thin.ttf";
    }

    @Override
    public String getBaseUri() {
        return baseUri;
    }

    @Override
    public String getTag() {
        return TAG;
    }
}


//    @NonNull
//    private Order newOrder() {
//        final Order o = new Order();
//        final Party party = new Party();
//        final List<OrderItem> orderItems = new ArrayList<>();
//        orderItems.add(new OrderItem(new Product(), 500, 1, "placed"));
//        party.setId(5);
//        o.setCode(UUID.randomUUID().toString());
//        o.setStatus("placed");
//        o.setParty(party);
//        o.setOrderItems(orderItems);
//        return o;
//    }

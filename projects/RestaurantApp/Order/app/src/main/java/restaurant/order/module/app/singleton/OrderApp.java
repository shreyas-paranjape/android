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
    public String getAccountType() {
        return "in.co.foodamigo.restaurant";
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

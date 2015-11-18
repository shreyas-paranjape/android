package in.co.foodamigo.customer.module.app.singleton;

import android.app.Application;

import com.order.CurrentOrderManager;
import com.orm.SugarContext;
import com.util.FontsOverride;

import model.DummyData;


public class CustomerApp extends Application {

    private final CurrentOrderManager currentOrderManager = new CurrentOrderManager();

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Raleway-Thin.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "Raleway-Regular.ttf");
        DummyData.createDummyData("food.customer");
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
package in.co.foodamigo.shipment.module.app;

import android.app.Application;

import com.orm.SugarContext;

import org.mapsforge.map.android.graphics.AndroidGraphicFactory;

public class ShipmentApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
        //AndroidGraphicFactory.createInstance(this);
    }

    @Override
    public void onTerminate() {
        SugarContext.terminate();
        super.onTerminate();
    }
}

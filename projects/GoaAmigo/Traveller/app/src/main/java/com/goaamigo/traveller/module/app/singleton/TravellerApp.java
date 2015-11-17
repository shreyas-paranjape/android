package com.goaamigo.traveller.module.app.singleton;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.goaamigo.traveller.module.product.view.Contoller.CurrentOrderManager;

public class TravellerApp extends Application {
    private final CurrentOrderManager currentOrderManager = new CurrentOrderManager();

    @Override
    public void onCreate() {
        super.onCreate();
        //SugarContext.init(getApplicationContext());

        //Profile profile = new Profile();
        // set properties
        //SugarRecord.save(profile);
    }

    @Override
    public void onTerminate() {
        //SugarContext.terminate();
        super.onTerminate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(this);
    }

    public CurrentOrderManager getCurrentOrderManager() {
        return currentOrderManager;
    }

}

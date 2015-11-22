package com.goaamigo.traveller.module.app.singleton;

import android.app.Application;
import android.content.Intent;

import com.order.CurrentOrderManager;
import com.orm.SugarContext;

import rest.common.DataService;

public class TravellerApp extends Application {
    private final CurrentOrderManager currentOrderManager = new CurrentOrderManager();

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
        startService(new Intent(getApplicationContext(), DataService.class));
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

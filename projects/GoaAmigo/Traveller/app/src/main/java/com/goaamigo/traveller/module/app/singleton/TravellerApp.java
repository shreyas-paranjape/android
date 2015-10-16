package com.goaamigo.traveller.module.app.singleton;

import android.app.Application;

import com.orm.SugarContext;

public class TravellerApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        SugarContext.terminate();
        super.onTerminate();

    }
}

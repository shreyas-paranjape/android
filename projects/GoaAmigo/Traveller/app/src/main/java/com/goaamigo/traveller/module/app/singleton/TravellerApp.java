package com.goaamigo.traveller.module.app.singleton;

import android.app.Application;

import model.trip.Profile;
import com.orm.SugarContext;
import com.orm.SugarRecord;

public class TravellerApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());

        Profile profile = new Profile();
        // set properties
        SugarRecord.save(profile);
    }

    @Override
    public void onTerminate() {
        SugarContext.terminate();
        super.onTerminate();

    }
}

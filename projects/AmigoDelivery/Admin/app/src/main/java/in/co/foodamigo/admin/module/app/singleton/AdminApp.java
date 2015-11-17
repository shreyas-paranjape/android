package in.co.foodamigo.admin.module.app.singleton;

import android.app.Application;

import com.orm.SugarContext;
import com.util.FontsOverride;

import model.DummyData;


public class AdminApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Raleway-Thin.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "Raleway-Regular.ttf");
        DummyData.createDummyData("goaamigo.admin");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}

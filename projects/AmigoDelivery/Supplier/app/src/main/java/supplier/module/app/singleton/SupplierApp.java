package supplier.module.app.singleton;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.orm.SugarContext;
import com.util.FontsOverride;

public class SupplierApp extends Application {

    private RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
        SugarContext.init(getApplicationContext());
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Raleway-Thin.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "Raleway-Regular.ttf");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

    public RequestQueue getQueue() {
        return queue;
    }

    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }
}

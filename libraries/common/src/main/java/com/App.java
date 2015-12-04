package com;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.DiskBasedCache;
import com.order.CurrentOrderManager;
import com.orm.SugarContext;
import com.util.Constant;
import com.util.FontsOverride;

import java.io.File;

import de.greenrobot.event.EventBus;

@SuppressWarnings("unused")
public abstract class App extends Application {

    private static final String DEFAULT_CACHE_DIR = "volley";
    protected final EventBus eventBus = EventBus.getDefault();
    protected final CurrentOrderManager currentOrderManager = new CurrentOrderManager();
    protected RequestQueue queue;
    private SharedPreferences sharedPref;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            if (null != getSharedPrefKey()) {
                sharedPref = getSharedPreferences(getSharedPrefKey(), MODE_PRIVATE);
            } else {
                throw new RuntimeException("Please specify Shared pref key");
            }
            initHttpQueue();
            initSugarDb();
            setAppFonts();
        } catch (Throwable t) {
            logError(App.class.getName(), "onCreate", "Error during app create: " + t.getMessage());
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        try {
            SugarContext.terminate();
            queue.stop();
        } catch (Throwable t) {
            logError(App.class.getName(), "onTerminate", "Error during app terminate: " + t.getMessage());
        }
    }

    public void logError(String className, String methodName, String message) {
        Log.e(getTag(), getMessage(className, methodName, message));
    }

    public void logInfo(String className, String methodName, String message) {
        Log.i(getTag(), getMessage(className, methodName, message));
    }

    public void logDebug(String className, String methodName, String message) {
        Log.d(getTag(), getMessage(className, methodName, message));
    }

    public void logWarn(String className, String methodName, String message) {
        Log.w(getTag(), getMessage(className, methodName, message));
    }

    public RequestQueue getQueue() {
        return queue;
    }

    public CurrentOrderManager getCurrentOrderManager() {
        return currentOrderManager;
    }

    public void unRegisterListener(Object... listeners) {
        for (Object listener : listeners) {
            if (eventBus.isRegistered(listener)) {
                eventBus.unregister(listener);
            }
        }
    }

    public void registerListener(Object... listeners) {
        for (Object listener : listeners) {
            if (!eventBus.isRegistered(listener)) {
                eventBus.register(listener);
            }
        }
    }

    protected void registerListenerSticky(Object... listeners) {
        for (Object listener : listeners) {
            if (!eventBus.isRegistered(listener)) {
                eventBus.registerSticky(listener);
            }
        }
    }

    public void saveToPref(String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getFromPref(String key) {
        return sharedPref.getString(key, null);
    }

    public abstract String getBaseUri();

    public abstract String getTag();

    protected String getRegularFontAssetName() {
        return null;
    }

    protected String getThinFontAssetName() {
        return null;
    }

    public abstract String getAccountType();


    private void setAppFonts() {
        if (getThinFontAssetName() != null) {
            FontsOverride.setDefaultFont(this, "MONOSPACE", getThinFontAssetName());
        }
        if (getRegularFontAssetName() != null) {
            FontsOverride.setDefaultFont(this, "SERIF", getRegularFontAssetName());
        }
    }

    private void initSugarDb() {
        SugarContext.init(getApplicationContext());
    }

    private void initHttpQueue() {
        queue = RequestQueue
                .build(getCache())
                .start();
    }

    @NonNull
    private DiskBasedCache getCache() {
        return new DiskBasedCache(
                new File(getExternalCacheDir() != null ?
                        getExternalCacheDir() : getCacheDir(),
                        DEFAULT_CACHE_DIR));
    }

    @NonNull
    private String getMessage(String className, String methodName, String message) {
        return "Class: " + className + " ; Method: " + methodName + " ; Message: " + message;
    }

    public String getMetaDataValue(String key) {
        try {
            ApplicationInfo ai = getPackageManager()
                    .getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            return ai.metaData.getString(key);
        } catch (Exception e) {
            logError(App.class.getName(), "getMetaDataValue", "Error : " + e.getMessage());
            return null;
        }
    }


    public String getSharedPrefKey() {
        return "";
    }

    public String[] getAppCredentials() {
        return new String[]{
                "",
                ""
        };
    }

    public String getToken() {
        return getFromPref(Constant.KEY_USER_TOKEN);
    }

    public String getSecretKey() {
        return null;
    }

}

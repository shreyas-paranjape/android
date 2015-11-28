package com;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.DiskBasedCache;
import com.order.CurrentOrderManager;
import com.orm.SugarContext;
import com.util.FontsOverride;

import java.io.File;

import de.greenrobot.event.EventBus;

@SuppressWarnings("unused")
public abstract class App extends Application {

    private static final String DEFAULT_CACHE_DIR = "volley";
    protected final EventBus eventBus = EventBus.getDefault();
    protected final CurrentOrderManager currentOrderManager = new CurrentOrderManager();
    protected RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
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

    public abstract String getBaseUri();

    public abstract String getTag();

    @NonNull
    protected abstract String getRegularFontAssetName();

    @NonNull
    protected abstract String getThinFontAssetName();

    public abstract String getAccountType();


    private void setAppFonts() {
        FontsOverride.setDefaultFont(this, "MONOSPACE", getThinFontAssetName());
        FontsOverride.setDefaultFont(this, "SERIF", getRegularFontAssetName());
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


}

package com.rest.toolbox;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.android.volley.RequestQueue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.util.AsyncExecutor;

public class DataService extends Service {

    private static final String TAG = DataService.class.getName();
    private RequestQueue dataRequestQueue;
    private ScheduledExecutorService executorService;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Started service");
        executorService = Executors.newScheduledThreadPool(1);
        //dataRequestQueue = Volley.newRequestQueue(getApplicationContext());
        syncData();
    }

    private void syncData() {
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                AsyncExecutor.create().execute(new AsyncExecutor.RunnableEx() {
                    @Override
                    public void run() throws Exception {
                        EventBus.getDefault().postSticky(new SyncCompleteEvent());
                    }
                });
            }
        }, 5, TimeUnit.SECONDS);
    }

    public static class SyncCompleteEvent {
    }
}

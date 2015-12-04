package com.admin.module.app.infra.socket;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.App;
import com.admin.R;

import org.java_websocket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import de.greenrobot.event.EventBus;

public class SocketConnectionManager extends Service {

    private static final String TAG = SocketConnectionManager.class.getName();
    private final WebSocketClient client;
    private static final String endPoint = "ws://192.168.10.250:3000/connect";
    private static final EventBus eventBus = EventBus.getDefault();
    private NotificationManager mNotificationManager;
    private static final Random random = new Random();


    public SocketConnectionManager() {
        eventBus.register(this);
        URI endpointUri;
        try {
            endpointUri = new URI(endPoint + "?token=" + getApp().getToken());
        } catch (URISyntaxException e) {
            Log.e(TAG, e.getMessage());
            throw new RuntimeException(e);
        }
        client = new SocketClient(endpointUri);
    }

    private App getApp() {
        return (App) getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        client.connect();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressWarnings("unused")
    public void onEvent(SocketClient.DataReceivedEvent event) {
        showNotification(event.getData());
    }


    @SuppressWarnings("unused")
    public void onEvent(SocketClient.SocketClosedEvent event) {
        getApp().logDebug(
                SocketConnectionManager.class.getName(),
                "onEvent",
                "Socket closed. Reason: " + event.getReason() +
                        " Code: " + event.getCode() +
                        " Remote: " + event.isRemote());
    }


    private void showNotification(String data) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("New order")
                        .setContentText(data);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, data);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                this,
                0,
                sendIntent,
                PendingIntent.FLAG_ONE_SHOT
        );
        mBuilder.setContentIntent(resultPendingIntent);
        mNotificationManager.notify(random.nextInt(), mBuilder.build());

    }
}

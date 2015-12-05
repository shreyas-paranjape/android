package com.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.App;

import org.java_websocket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

public class SocketConnectionManager extends Service {

    private static final String TAG = SocketConnectionManager.class.getName();
    private WebSocketClient client;
    private URI endpointUri;
    private static final String endPoint = "ws://192.168.10.172:3000/connect";

    private App getApp() {
        return (App) getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            endpointUri = new URI(endPoint + "?token=" + "peep_peep");
        } catch (URISyntaxException e) {
            Log.e(TAG, e.getMessage());
            throw new RuntimeException(e);
        }
        client = new SocketClient(endpointUri);
        getApp().registerListener(this);
        client.connect();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressWarnings("unused")
    public void onEvent(SocketClient.DataReceivedEvent event) {
        getApp().logInfo(TAG, "onEvent(DataReceivedEvent)", event.getData());
    }

    @SuppressWarnings("unused")
    public void onEvent(SocketClient.SocketOpenedEvent event) {
        client.send("Hello");
    }


    @SuppressWarnings("unused")
    public void onEvent(SocketClient.SocketClosedEvent event) {
        getApp().logDebug(
                TAG,
                "onEvent(SocketClosedEvent)",
                "Socket closed. Reason: " + event.getReason() +
                        " Code: " + event.getCode() +
                        " Remote: " + event.isRemote());
    }

    @SuppressWarnings("unused")
    public void onEvent(CloseSocketConnection event) {
        client.close();
        client = null;
    }

    @SuppressWarnings("unused")
    public void onEvent(AttemptSocketConnectionEvent event) {
        client = new SocketClient(endpointUri);
        client.connect();
    }


    @Override
    public void onDestroy() {
        getApp().unRegisterListener(this);
        client.close();
        super.onDestroy();
    }

    public static class AttemptSocketConnectionEvent {
    }

    public static class CloseSocketConnection {
    }
}


//mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//private NotificationManager mNotificationManager;
//private static final Random random = new Random();

//showNotification(event.getData());

//    private void showNotification(String data) {
//        NotificationCompat.Builder mBuilder =
//                new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setContentTitle("New order")
//                        .setContentText(data);
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, data);
//        sendIntent.setType("text/plain");
//        sendIntent.setPackage("com.whatsapp");
//        PendingIntent resultPendingIntent = PendingIntent.getActivity(
//                this,
//                0,
//                sendIntent,
//                PendingIntent.FLAG_ONE_SHOT
//        );
//        mBuilder.setContentIntent(resultPendingIntent);
//        mNotificationManager.notify(random.nextInt(), mBuilder.build());
//
//    }
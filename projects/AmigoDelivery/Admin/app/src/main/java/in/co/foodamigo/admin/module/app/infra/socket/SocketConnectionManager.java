package in.co.foodamigo.admin.module.app.infra.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


import org.java_websocket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

import de.greenrobot.event.EventBus;

public class SocketConnectionManager extends Service {

    private static final String TAG = SocketConnectionManager.class.getName();
    private final WebSocketClient client;
    private static final String endPoint = "ws://192.168.10.250:3000/connect";
    private static final EventBus eventBus = EventBus.getDefault();

    public SocketConnectionManager() {
        eventBus.register(this);
        URI endpoint = null;
        try {
            endpoint = new URI(endPoint);
        } catch (URISyntaxException e) {
            Log.e(TAG, e.getMessage());
        }
        client = new SocketClient(endpoint);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        client.connect();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onEvent(SocketClient.SocketClosedEvent event) {
        Log.d(TAG,
                "Socket closed. Reason: " + event.getReason() +
                        " Code: " + event.getCode() +
                        " Remote: " + event.isRemote());
    }

}

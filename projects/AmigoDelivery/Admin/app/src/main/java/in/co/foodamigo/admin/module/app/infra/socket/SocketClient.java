package in.co.foodamigo.admin.module.app.infra.socket;

import android.provider.Telephony;
import android.util.Log;


import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

import de.greenrobot.event.EventBus;

public class SocketClient extends WebSocketClient {

    private final EventBus eventBus = EventBus.getDefault();
    private final String TAG = SocketClient.class.getName();

    public SocketClient(URI serverURI) {
        super(serverURI);
    }

    public SocketClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public SocketClient(URI serverUri, Draft draft, Map<String, String> headers, int connecttimeout) {
        super(serverUri, draft, headers, connecttimeout);
    }

    @Override
    public void onOpen(ServerHandshake data) {
        eventBus.register(this);
        Log.i(TAG, "server handshake data : " + data.getHttpStatus() + "; " + data.getHttpStatusMessage());
        eventBus.post(new SocketOpenedEvent(data));
    }

    @Override
    public void onMessage(String message) {
        Log.i(TAG, "message : " + message);
        eventBus.post(new DataReceivedEvent(message));
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.i(TAG, "code : " + code + "; reason : " + reason + "; remote: " + remote);
        eventBus.post(new SocketClosedEvent(code, reason, remote));
        if (eventBus.isRegistered(this)) {
            eventBus.unregister(this);
        }
    }

    @Override
    public void onError(Exception ex) {
    }

    public void onEvent(SocketClient.DataSendEvent event) {
        send(event.getData());
    }

    public static class DataSendEvent {
        private final String data;

        public DataSendEvent(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }

    public static class DataReceivedEvent {
        private final String data;

        public DataReceivedEvent(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }

    public static class SocketOpenedEvent {
        private final ServerHandshake data;

        public SocketOpenedEvent(ServerHandshake data) {
            this.data = data;
        }

        public ServerHandshake getData() {
            return data;
        }
    }

    public static class SocketClosedEvent {
        private final int code;
        private final String reason;
        private final boolean remote;

        public SocketClosedEvent(int code, String reason, boolean remote) {
            this.code = code;
            this.reason = reason;
            this.remote = remote;
        }

        public int getCode() {
            return code;
        }

        public String getReason() {
            return reason;
        }

        public boolean isRemote() {
            return remote;
        }
    }
}

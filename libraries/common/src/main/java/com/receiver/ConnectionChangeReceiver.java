package com.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.socket.SocketConnectionManager;

import de.greenrobot.event.EventBus;

public class ConnectionChangeReceiver extends BroadcastReceiver {

    private ConnectivityManager connectivityManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        ensureManagerInit(context);
        NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
        if (ni != null && ni.getState() == NetworkInfo.State.CONNECTED) {
            EventBus.getDefault().post(new SocketConnectionManager.AttemptSocketConnectionEvent());
        } else if (intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
            EventBus.getDefault().post(new SocketConnectionManager.CloseSocketConnection());
        }
    }

    private void ensureManagerInit(Context context) {
        if (connectivityManager == null) {
            connectivityManager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
    }


}

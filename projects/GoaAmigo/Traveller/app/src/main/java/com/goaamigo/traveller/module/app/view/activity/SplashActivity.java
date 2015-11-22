package com.goaamigo.traveller.module.app.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.goaamigo.traveller.R;
import com.view.activity.AbstractActivity;

import de.greenrobot.event.util.ThrowableFailureEvent;
import rest.common.DataService;

public class SplashActivity extends AbstractActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerListenerSticky(this);
        ViewGroup mainFrame = ((ViewGroup) findViewById(R.id.root));
        mainFrame.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoom_out));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegisterListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    public void onEventMainThread(DataService.SyncCompleteEvent event) {
        startNewActivity(HomeActivity.class, new Bundle());
        finish();
    }

    public void onEventMainThread(ThrowableFailureEvent event) {
        Log.d("APP", "Got error");
    }
}

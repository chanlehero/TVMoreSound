package com.ch.tvmoresound.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by chenhao on 2018/2/24.
 */

public class AudioBroadcastService extends IntentService {

    private static final String TAG = "AudioBroadcastService";

    public AudioBroadcastService(){
        super("AudioBroadcastService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate========="+this.hashCode());
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "onStart========="+this.hashCode());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "onHandleIntent========="+this.hashCode());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy========="+this.hashCode());
    }
}

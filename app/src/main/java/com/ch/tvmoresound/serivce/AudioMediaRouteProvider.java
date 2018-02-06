package com.ch.tvmoresound.serivce;

import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.media.MediaControlIntent;
import android.support.v7.media.MediaRouteProvider;

import java.util.ArrayList;

/**
 * Created by chenhao on 2018/2/6.
 */

public class AudioMediaRouteProvider extends MediaRouteProvider {

    private static final ArrayList<IntentFilter> CONTROL_FILTERS_BASIC;
    private static final String TAG = "AudioMediaRouteProvider";

    static {
        IntentFilter audioFilter = new IntentFilter();
        audioFilter.addCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO);
        audioFilter.addAction(MediaControlIntent.ACTION_PLAY);
        audioFilter.addAction(MediaControlIntent.ACTION_STOP);
        CONTROL_FILTERS_BASIC = new ArrayList<IntentFilter>();
        CONTROL_FILTERS_BASIC.add(audioFilter);
    }

    public AudioMediaRouteProvider(Context context) {
        super(context);
    }
}
